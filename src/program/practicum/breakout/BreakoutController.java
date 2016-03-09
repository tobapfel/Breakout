package program.practicum.breakout;

import java.awt.Point;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;

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
		this.model.setBallPositionX(
				((view.PADDLE_WIDTH - view.BALL_SIZE) / 2) - view.PADDLE_WIDTH / 2 + this.model.getPaddlePosition());
		this.model.setBallPositionY(view.PADDLE_Y - 5 - view.BALL_SIZE);
		stopGame();
	}
	
	public void stopGame() {
		this.model.setBallDeltaX(0);
		this.model.setBallDeltaY(0);
	}
	
	public void startGame() {
		this.model.setBallDeltaX(0);
		this.model.setBallDeltaY(-1 * this.view.BALL_SPEED);
	}

	public void mouseMoved(MouseEvent event) {
		this.model.setPaddlePosition(event.getX());
		if (!mode){
			setBallToStart();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (!mode) {
			startGame();
		} else{
			setBallToStart();
		}
		mode = !mode;
	}

	public void ballMovement() {
		this.model.setBallPositionX(this.model.getBallPositionX() + this.model.getBallDeltaX());
		this.model.setBallPositionY(this.model.getBallPositionY() + this.model.getBallDeltaY());
	}

	public boolean collisionAt(Point p) {
		GObject e = this.view.getElementAt(p.getX(), p.getY());
		if (e != null) {
			// Paddle / Brick
			if (e.getClass().getSimpleName().equals("Paddle")) {
				// Paddle handling
				double paddleDistance = this.model.getBallPositionX() - this.model.getPaddlePosition();
				System.out.println(paddleDistance);
				this.model.setBallDeltaX((paddleDistance / this.view.PADDLE_WIDTH) * 5);
				return true;
			}
			if (e.getClass().getSimpleName().equals("Bricks")) {
				this.model.removeBrick(e);
				return true;
			}
		}
		return false;
	}

	public Point topLeftCorner() {
		return new Point((int) this.model.getBallPositionX(), (int) this.model.getBallPositionY());
	}

	public Point topRightCorner() {
		return new Point((int) (this.model.getBallPositionX() + this.view.BALL_SIZE),
				(int) this.model.getBallPositionY());
	}

	public Point bottomLeftCorner() {
		return new Point((int) this.model.getBallPositionX(),
				(int) (this.model.getBallPositionY() + this.view.BALL_SIZE));
	}

	public Point bottomRightCorner() {
		return new Point((int) (this.model.getBallPositionX() + this.view.BALL_SIZE),
				(int) (this.model.getBallPositionY() + this.view.BALL_SIZE));
	}

	public void ballCollision() {
		// window left edge
		if (topLeftCorner().getX() < 0) {
			this.model.setBallDeltaX(-1 * this.model.getBallDeltaX());
		}
		// window right edge
		else if (topRightCorner().getX() > this.view.getWidth()) {
			this.model.setBallDeltaX(-1 * this.model.getBallDeltaX());
		}
		// window bottom edge
		else if (bottomLeftCorner().getY() > this.view.getHeight()) {
			//this.model.setBallDeltaY(-1 * this.model.getBallDeltaY());
			// Leben entfernen!
			this.setBallToStart();
			this.mode = false;
			this.model.lives -= 1;
		}
		// window top edge
		else if (topLeftCorner().getY() < 0) {
			this.model.setBallDeltaY(-1 * this.model.getBallDeltaY());
		}
		// horizontal edge collides with object
		else if (!(collisionAt(topLeftCorner()) && collisionAt(topRightCorner()))
				&& (collisionAt(bottomLeftCorner()) && collisionAt(bottomRightCorner()))) {
			this.model.setBallDeltaY(-1 * this.model.getBallDeltaY());
		} else if ((collisionAt(topLeftCorner()) && collisionAt(topRightCorner()))
				&& !(collisionAt(bottomLeftCorner()) && collisionAt(bottomRightCorner()))) {
			this.model.setBallDeltaY(-1 * this.model.getBallDeltaY());
		}

		// vertical edge collides with object
		else if (!(collisionAt(topLeftCorner()) && collisionAt(bottomLeftCorner()))
				&& (collisionAt(topRightCorner()) && collisionAt(bottomRightCorner()))) {
			this.model.setBallDeltaX(-1 * this.model.getBallDeltaX());
		} else if ((collisionAt(topLeftCorner()) && collisionAt(bottomLeftCorner()))
				&& !(collisionAt(topRightCorner()) && collisionAt(bottomRightCorner()))) {
			this.model.setBallDeltaX(-1 * this.model.getBallDeltaX());
		}
	}
	/**
	 * true = lives are left
	 * false = no live is left
	 * @return
	 */
	public boolean checkLiveLeft (){
		if (this.model.lives == 0){
			return false;
		}
		return true;
	}

	public void updateController() {
		ballCollision();
		ballMovement();
	}

}
