package program.practicum.breakout;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
/**
 * A View for playing Breakout
 */

public class View extends GraphicsProgram {
	BreakoutModel model;
	BreakoutController controller;
	
	public int WINDOW_WIDTH;
	public int WINDOW_HEIGHT;

	public int ROWS;
	public int COLUMNS;
	private int LIVES;

	public int PADDLE_WIDTH;
	private int PADDLE_HEIGHT;
	public int PADDLE_Y;

	public int BALL_SIZE;
	public int BALL_SPEED;
	
	Bricks[][] bricks;
	private Ball ball;
	private Paddle paddle;
	
	/**
	 * Create a new View
	 * @param settings The settings that will be used for this game
	 */
	public View(Settings settings){
		// read the settings
		this.WINDOW_WIDTH = settings.get("WINDOW_WIDTH");
		this.WINDOW_HEIGHT = settings.get("WINDOW_HEIGHT");
		
		this.ROWS = settings.get("ROWS");
		this.COLUMNS = settings.get("COLUMNS");
		this.LIVES = settings.get("LIVES");
		
		this.PADDLE_WIDTH = (int)(this.WINDOW_WIDTH * 0.25);
		this.PADDLE_HEIGHT = settings.get("PADDLE_HEIGHT");
		this.PADDLE_Y = (int)(this.WINDOW_HEIGHT * 0.85);
		
		this.BALL_SIZE = settings.get("BALL_SIZE");
		
		this.BALL_SPEED = settings.get("BALL_SPEED");
	}

	/**
	 * Run-method of this view
	 * Create a model and controller instance.
	 * Add mouse listeners.
	 * Set up the bricks.
	 */
	@Override
	public void run() {
		setSize(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
		this.model = new BreakoutModel(this, ROWS, COLUMNS, LIVES);
		this.controller = new BreakoutController(this);

		this.addMouseListeners();

		this.bricks = new Bricks[this.model.getBrickRowCount()][this.model.getBrickColumnCount()];

		for (int i = 0; i < this.model.getBrickRowCount(); i++) {
			for (int j = 0; j < this.model.getBrickColumnCount(); j++) {
				Bricks brick = new Bricks(i, j, ROWS, COLUMNS, getHeight(), getWidth());
				bricks[i][j] = brick;
				this.model.setBrick(i, j);

			}
		}
	}

	/**
	 * The mouse has been moved:
	 * pass the event to the controller
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		this.controller.mouseMoved(e);
	}

	/**
	 * The mouse has been clicked:
	 * pass the event to the controller
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.controller.mouseClicked(e);
	}

	/**
	 * Remove all GObjects on the Canvas
	 * and create new objects for the paddle, ball and bricks.
	 * 
	 * If there are no lives left or all bricks destroyed,
	 * show the GameOver-screen.
	 */
	public void updateView() {
		this.removeAll();
		// the ball needs to be at the bottom for correct collision detection
		this.updateBall();
		this.updatePaddle();
		this.updateBricks();
		if (!this.controller.checkLiveLeft()){
			gameOverScreen();
			this.controller.stopGame();
			this.controller.mode = 4;
		}
		if (this.model.checkGameOver()){
			this.controller.stopGame();
			this.controller.mode = 3;
			this.controller.setBallToStart();
			for (int i = 0; i < this.model.getBrickRowCount(); i++) {
				for (int j = 0; j < this.model.getBrickColumnCount(); j++) {
					Bricks brick = new Bricks(i, j, ROWS, COLUMNS, getHeight(), getWidth());
					bricks[i][j] = brick;
					this.model.setBrick(i, j);

				}
			}
			continueScreen();
		}
		if (this.controller.mode == 3){
			continueScreen();
		}
	
		this.add(new GLabel("" + this.model.getScore(), this.WINDOW_WIDTH * 0.1, this.WINDOW_HEIGHT * 0.9));
	}

	/**
	 * Create a new Paddle and set it to the PaddlePosition
	 */
	public void updatePaddle() {
		this.add(new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT), this.model.getPaddlePosition() - PADDLE_WIDTH / 2, this.PADDLE_Y);
	}

	/**
	 * Create a new Ball and set it the BallPosition
	 */
	public void updateBall() {
		this.add(new Ball(BALL_SIZE), this.model.getBallPositionX(),
				this.model.getBallPositionY());
	}

	/**
	 * Create the bricks and set them to their positions
	 */
	public void updateBricks() {
		for (int i = 0; i < this.model.getBrickRowCount(); i++) {
			for (int j = 0; j < this.model.getBrickColumnCount(); j++) {
				Bricks b = bricks[i][j];
				if (this.model.bricks[i][j] == true) {
					add(b);
				} 
			}
		}
	}
	
	/**
	 * Show the GameOverScreen
	 */
	public void gameOverScreen(){
		GLabel label = new GLabel("GAME OVER");
		label.setFont("arial-bold-70");
		FontMetrics fm = label.getFontMetrics();
		int lineHeight = fm.getHeight();
		int lineWidth = fm.stringWidth("GAME OVER");
		label.setLocation((WINDOW_WIDTH - lineWidth) / 2, (WINDOW_HEIGHT - lineHeight) / 2);
		label.setColor(Color.BLACK);
		add (label);
		
	}
	
	public void continueScreen (){
		GLabel label = new GLabel("CONTINUE ?");
		GLabel label2 = new GLabel("Click Mouse to Continue");
		label.setFont("arial-bold-70");
		label2.setFont("arial-30");
		FontMetrics fm = label.getFontMetrics();
		FontMetrics fm2 = label2.getFontMetrics();
		int lineHeight = fm.getHeight();
		int lineWidth = fm.stringWidth("CONTINUE ?");
		int lineHeight2 = fm2.getHeight();
		int lineWidth2 = fm2.stringWidth("Click Mouse to Continue");
		label.setLocation((WINDOW_WIDTH - lineWidth) / 2, (WINDOW_HEIGHT - lineHeight) / 2);
		label.setColor(Color.BLACK);
		label2.setLocation((WINDOW_WIDTH - lineWidth2) / 2, ((WINDOW_HEIGHT - lineHeight2) / 2) + 20);
		label2.setColor(Color.BLACK);
		add (label);
		add (label2);
		
	}
	
	/*public void heartImg (){ //anzeige flackert aufgrund des image
		GImage img = new GImage("Heart.png", 500, 500);
		add (img);
	}
	*/
}
