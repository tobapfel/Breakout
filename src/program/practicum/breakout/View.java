package program.practicum.breakout;

import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;

public class View extends GraphicsProgram {
	BreakoutModel model;
	BreakoutController controller;

	public final int ROWS = 5;
	public final int COLUMNS = 5;
	private final int LIVES = 3;

	public final int PADDLE_WIDTH = 100;
	private final int PADDLE_HEIGHT = 10;

	public final int BALL_SIZE = 10;
	Bricks[][] bricks;
	private Ball ball;
	private Paddle paddle;

	@Override
	public void run() {
		setSize(500, 500);
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
		this.add(new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT), this.model.getPaddlePosition() - PADDLE_WIDTH / 2, 400);
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
