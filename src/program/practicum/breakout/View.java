package program.practicum.breakout;

import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;

public class View extends GraphicsProgram {
	BreakoutModel model;
	BreakoutController controller;
	
	private int WINDOW_WIDTH;
	private int WINDOW_HEIGHT;

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
	
	public View(Settings settings){
		this.WINDOW_WIDTH = settings.get("WINDOW_WIDTH");
		this.WINDOW_HEIGHT = settings.get("WINDOW_HEIGHT");
		
		this.ROWS = settings.get("ROWS");
		this.COLUMNS = settings.get("COLUMNS");
		this.LIVES = settings.get("LIVES");
		
		this.PADDLE_WIDTH = (int)(this.WINDOW_WIDTH * 0.3);
		this.PADDLE_HEIGHT = settings.get("PADDLE_HEIGHT");
		this.PADDLE_Y = (int)(this.WINDOW_HEIGHT * 0.85);
		
		this.BALL_SIZE = settings.get("BALL_SIZE");
		
		this.BALL_SPEED = settings.get("BALL_SPEED");
	}

	@Override
	public void run() {
		setSize(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
		this.model = new BreakoutModel(this, ROWS, COLUMNS, LIVES);
		this.controller = new BreakoutController(this);

		this.addMouseListeners();

		bricks = new Bricks[this.model.getBrickRowCount()][this.model.getBrickColumnCount()];

		for (int i = 0; i < this.model.getBrickRowCount(); i++) {
			for (int j = 0; j < this.model.getBrickColumnCount(); j++) {
				Bricks brick = new Bricks(i, j, ROWS, COLUMNS, getHeight(), getWidth());
				bricks[i][j] = brick;
				this.model.setBrick(i, j);

			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.controller.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.controller.mouseClicked(e);
	}

	public void updateView() {
		this.removeAll();
		// the ball needs to be at the bottom for correct collision detection
		this.updateBall();
		this.updatePaddle();
		this.updateBricks();
	}

	public void updatePaddle() {
		this.add(new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT), this.model.getPaddlePosition() - PADDLE_WIDTH / 2, this.PADDLE_Y);
	}

	public void updateBall() {
		this.add(new Ball(BALL_SIZE), this.model.getBallPositionX(),
				this.model.getBallPositionY());
	}

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
}
