package program.practicum.breakout;

import java.awt.Point;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;

public class BreakoutController {

	public BreakoutModel model;
	public View view;
	public int mode = 1; // 1 = Stop / 2 = Start / 3 = Continue? / 4 = GameOver

	/**
	 * Create a new BreakoutController
	 * @param view The view that should be controlled
	 */
	public BreakoutController(View view) {
		this.view = view;
		this.model = view.model;
		setBallToStart();
	}
	
	/**
	 * Put the ball on the paddle and set it's speed to 0.
	 */
	public void setBallToStart() {
		this.model.setBallPositionX(
				((view.PADDLE_WIDTH - view.BALL_SIZE) / 2) - view.PADDLE_WIDTH / 2 + this.model.getPaddlePosition());
		this.model.setBallPositionY(view.PADDLE_Y - 5 - view.BALL_SIZE);
		stopGame();
	}
	
	/**
	 * Stop the game
	 */
	public void stopGame() {
		this.model.setBallDeltaX(0);
		this.model.setBallDeltaY(0);
	}
	
	/**
	 * Start the game
	 */
	public void startGame() {
		this.model.setBallDeltaX(0);
		this.model.setBallDeltaY(-1 * this.view.BALL_SPEED);
	}


	/**
	 *  Update the paddlePosition
	 * @param e
	 */
	public void mouseMoved(MouseEvent e) {
		this.model.setPaddlePosition(e.getX());
		if (mode == 1 || mode == 3){
			setBallToStart();
		}
	}

	/**
	 * Start the game or reset the ball or starts next level
	 */
	public void mouseClicked(MouseEvent e) {
		// starts the game
		if (mode == 1) {
			startGame();
			mode = 2;
		} 
		// resets the ball and lose a live
		else if (mode == 2){
			setBallToStart();
			mode = 1;
			this.model.loseLive();
		}
		// start next level
		else if (mode == 3){
			setBallToStart();
			mode = 1;
		}
	}
	
	/**
	 * Calculate the next ball position.
	 * The ball is moving by DeltaX/DeltaY pixels each frame.
	 */
	public void ballMovement() {
		this.model.setBallPositionX(this.model.getBallPositionX() + this.model.getBallDeltaX());
		this.model.setBallPositionY(this.model.getBallPositionY() + this.model.getBallDeltaY());
	}

	/**
	 * Detect if there was a collision at this point
	 * by looking at the topmost element.
	 * 
	 * If it collides with the paddle, change the rebound-angle of the ball according
	 * the point where it hit the paddle.
	 * 
	 * If it collides with a brick, this brick will be removed from the field. 
	 * 
	 * @param p The point to check
	 * @return TRUE if there was a collision. FALSE if there wasn't.
	 */
	public boolean collisionAt(Point p) {
		GObject e = this.view.getElementAt(p.getX(), p.getY());
		if (e != null) {
			// Paddle / Brick
			if (e.getClass().getSimpleName().equals("Paddle")) {
				// Paddle handling
				double paddleDistance = this.model.getBallPositionX() - this.model.getPaddlePosition();
				this.model.setBallDeltaX((paddleDistance / this.view.PADDLE_WIDTH) * 8);
				return true;
			}
			// Brick handling
			if (e.getClass().getSimpleName().equals("Bricks")) {
				this.model.removeBrick(e);
				return true;
			}
		}
		return false;
	}

	/**
	 * Shortcut for the Point at the top left corner of the ball
	 * @return the point
	 */
	public Point topLeftCorner() {
		return new Point((int) this.model.getBallPositionX(), (int) this.model.getBallPositionY());
	}

	/**
	 * Shortcut for the Point at the top right corner of the ball
	 * @return the point
	 */
	public Point topRightCorner() {
		return new Point((int) (this.model.getBallPositionX() + this.view.BALL_SIZE),
				(int) this.model.getBallPositionY());
	}

	/**
	 * Shortcut for the Point at the bottom left corner of the ball
	 * @return the point
	 */
	public Point bottomLeftCorner() {
		return new Point((int) this.model.getBallPositionX(),
				(int) (this.model.getBallPositionY() + this.view.BALL_SIZE));
	}

	/**
	 * Shortcut for the Point at the bottom right corner of the ball
	 * @return the point
	 */
	public Point bottomRightCorner() {
		return new Point((int) (this.model.getBallPositionX() + this.view.BALL_SIZE),
				(int) (this.model.getBallPositionY() + this.view.BALL_SIZE));
	}

	/**
	 * Check for ball collisions.
	 * 
	 * If there was a collision change the ball direction,
	 * so that it rebounces of that surface
	 * 
	 * The window egdes are easy:
	 * Check if one of the corners nearest to the edge if it 
	 * 
	 * ex. a collision with the right edge
	 *     ____|_x
	 *    |    | |
	 * -> |    | |
	 *    |____|_|
	 *         |
	 *         
	 * For the collision with an object: check all four corners.
	 * one corner must be in. one edge must be out.
	 * an egde is in, if one of it's corners is in. out: vice versa
	 * 
	 * * ex. a collision with the right edge
	 * 	 OUT     IN
	 *    x____|_x
	 *    |    | |
	 * -> |    | |
	 *    x____|_x
	 *         |
	 */
	public void ballCollision() {
		// window left/right edge
		if (topLeftCorner().getX() < 0 || topRightCorner().getX() > this.view.getWidth()) {
			this.model.setBallDeltaX(-1 * this.model.getBallDeltaX());
		}
		// window bottom edge
		else if (bottomLeftCorner().getY() > this.view.getHeight()) {
			//this.model.setBallDeltaY(-1 * this.model.getBallDeltaY());
			this.setBallToStart();
			this.mode = 1;
			this.model.loseLive();;
		}
		// window top edge
		else if (topLeftCorner().getY() < 0) {
			this.model.setBallDeltaY(-1 * this.model.getBallDeltaY());
		}
		
		// horizontal edge collides with object
		else if (!(collisionAt(topLeftCorner()) || collisionAt(topRightCorner()))
				&& (collisionAt(bottomLeftCorner()) || collisionAt(bottomRightCorner()))) {
			// top edge is out. bottom edge is in
			this.model.setBallDeltaY(-1 * this.model.getBallDeltaY());
		} else if ((collisionAt(topLeftCorner()) || collisionAt(topRightCorner()))
				&& !(collisionAt(bottomLeftCorner()) || collisionAt(bottomRightCorner()))) {
			// top edge is in. bottom edge is out
			this.model.setBallDeltaY(-1 * this.model.getBallDeltaY());
		}

		// vertical edge collides with object
		else if (!(collisionAt(topLeftCorner()) || collisionAt(bottomLeftCorner()))
				&& (collisionAt(topRightCorner()) || collisionAt(bottomRightCorner()))) {
			// left edge is out. right edge is in.
			this.model.setBallDeltaX(-1 * this.model.getBallDeltaX());
		} else if ((collisionAt(topLeftCorner()) || collisionAt(bottomLeftCorner()))
				&& !(collisionAt(topRightCorner()) || collisionAt(bottomRightCorner()))) {
			// left edge is in. right edge is out
			this.model.setBallDeltaX(-1 * this.model.getBallDeltaX());
		}
	}
	
	/**
	 * true = lives are left
	 * false = no live is left
	 * @return
	 */
	public boolean checkLiveLeft (){
		if (this.model.getLives() == 0){
			return false;
		}
		return true;
	}

	/**
	 * Update the ball collision
	 * and ball movement
	 */
	public void updateController() {
		ballCollision();
		ballMovement();
	}

}
