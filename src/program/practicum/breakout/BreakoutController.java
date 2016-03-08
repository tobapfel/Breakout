package program.practicum.breakout;

import java.awt.event.MouseEvent;

public class BreakoutController {

	public BreakoutModel model;
	public View view;

	public BreakoutController(View view) {
		this.view = view;
		this.model = view.model;
	}

	public void mouseMoved(MouseEvent event) {
		this.model.setPaddlePosition(event.getX());
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("CLICK!");
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
