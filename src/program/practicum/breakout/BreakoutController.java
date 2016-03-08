package program.practicum.breakout;

import java.awt.event.MouseEvent;

public class BreakoutController {

	public BreakoutModel model;
	public View view;
	private boolean mode = false; // false = at the beginning / true = after
									// click

	public BreakoutController(View view) {
		this.view = view;
		this.model = view.model;
		setBallToStart();
	}

	private void setBallToStart() {
		this.model.setBallPositionX(((view.PADDLE_WIDTH - view.BALL_SIZE) / 2) + this.model.getPaddlePosition());
		this.model.setBallPositionY(300 - view.BALL_SIZE);
	}

	public void mouseMoved(MouseEvent event) {
		this.model.setPaddlePosition(event.getX());
		if (!mode)
			setBallToStart();

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void ballMovement() {
		this.model.setBallPositionX(this.model.getBallPositionX() + this.model.getBallDeltaX());
		this.model.setBallPositionY(this.model.getBallPositionY() + this.model.getBallDeltaY());
	}

	public void ballCollision() {

	}

	public void updateController() {
		// check for collisions and move the ball
		ballCollision();
		ballMovement();

		// System.out.println(this.model.getBallPositionX() + "," +
		// this.model.getBallPositionY());

		// break the bricks

	}

}
