package program.practicum.breakout;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class View extends GraphicsProgram {
	BreakoutModel model;
	BreakoutController controller;
	
	private final int ROWS = 1;
	private final int COLUMNS = 1;
	private final int LIVES = 3;
	
	private final int PADDLE_WIDTH = 100;
	private final int PADDLE_HEIGHT = 10;
	
	private final int BALL_SIZE = 10;
	
	public void run(){
		setSize(500, 500);
		this.model = new BreakoutModel(ROWS, COLUMNS, LIVES);
		this.controller = new BreakoutController(this);
	
		Bricks[][] bricks = new Bricks[this.model.getBrickRowCount()][this.model.getBrickColumnCount()];
	}

	
	/**
	 * removes a brick
	 * 
	 * @param brick
	 *            an object of type Bricks
	 */
	public void updateView() {
		this.removeAll();
		this.updatePaddle();
		this.updateBall();
	}
	
	public void updatePaddle(){
		this.add(new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT), this.model.getPaddlePosition() - PADDLE_HEIGHT / 2, 300);
	}
	
	public void updateBall(){
		this.add(new Ball(BALL_SIZE), this.model.getBallPositionX() - BALL_SIZE / 2, this.model.getBallPositionY() -  BALL_SIZE / 2);
	}
}
