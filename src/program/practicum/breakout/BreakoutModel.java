package program.practicum.breakout;

import acm.graphics.GObject;

public class BreakoutModel {
	private View view;
	public boolean[][] bricks;
	private int lives;

	private double ballPositionX;
	private double ballPositionY;
	private double ballDX = 0;
	private double ballDY = 0;
	private double paddlePositionX;

	private int score;
	private int level = 1;
	private int multiplikator = 1;

	/**
	 * Constructor
	 * 
	 * @param rows
	 *            [int] The number of brick rows
	 * @param columns
	 *            [int] The number of brick columns
	 * @param lives
	 *            [int] The number of lives, the user has. The game ends, when
	 *            there are no lives left.
	 */
	public BreakoutModel(View view, int rows, int columns, int lives) {
		if (rows < 1) {
			throw new IllegalArgumentException("The game needs at least 1 row of bricks. You've entered: " + rows);
		}
		if (columns < 1) {
			throw new IllegalArgumentException(
					"The game needs at least 1 column of bricks. You've entered: " + columns);
		}
		if (lives < 1) {
			throw new IllegalArgumentException("The player needs at least 1 live. You've entered: " + lives);
		}
		
		this.view = view;
		this.bricks = new boolean[rows][columns];
		this.lives = lives;
		this.score = 0;
	}

	/**
	 * Add the Brick at the given coordinate
	 * @param row [int] the row of the brick
	 * @param col [int] the column of the brick
	 */
	public void setBrick(int row, int col) {
		this.bricks[row][col] = true;
	}

	/**
	 * Remove the Brick at the given coordinate
	 * 
	 * @param row
	 *            [int] the row of the brick
	 * @param column
	 *            [int] the column of the brick
	 */
	public void removeBrick(int row, int column) {
		System.out.println("REMOVING BRICK: " + row + ", " + column);
		if(this.bricks[row][column] == true){
			this.addScore();
		}
		this.bricks[row][column] = false;
	}
	
	/**
	 * Remove this brick object
	 * @param b [GObject]
	 */
	public void removeBrick(GObject b){
		for (int i = 0; i < this.getBrickRowCount(); i++) {
			for (int j = 0; j < this.getBrickColumnCount(); j++) {
				if(b.equals(this.view.bricks[i][j])){
					this.removeBrick(i, j);
					return;
				}
			}
		}
	}
	/**
	 *  Checks if the game is over or not
	 * @return true when game over
	 */
	public boolean checkNoBricksLeft (){
		boolean check = true;
		for (int i = 0; i < this.getBrickRowCount(); i++) {
			for (int j = 0; j < this.getBrickColumnCount(); j++) {
				check = !(this.bricks [i][j]) && check;
				}
			}
		return check;
		}

	/**
	 * Get the number of bricks in each row
	 * @return the number of bricks in each row
	 */
	public int getBrickRowCount() {
		return this.bricks.length;
	}

	/**
	 * Get the number of bricks in each column
	 * @return the number of bricks in each column
	 */
	public int getBrickColumnCount() {
		return this.bricks[0].length;
	}
	
	/**
	 * Set the position of the paddle
	 * @param x
	 */
	public void setPaddlePosition(double x) {
		this.setPaddlePosition((int) x);
	}
	
	/**
	 * Set the position of the paddle
	 * @param x 
	 */
	public void setPaddlePosition(int x) {
		x = Math.max(x, this.view.PADDLE_WIDTH / 2);
		x = Math.min(x, this.view.WINDOW_WIDTH - (this.view.PADDLE_WIDTH / 2));
		this.paddlePositionX = x;
	}

	/**
	 * Get the position of the paddle
	 * @return the position of the paddle
	 */
	public double getPaddlePosition() {
		return this.paddlePositionX;
	}
	
	/**
	 * Get the position of the ball in x-direction
	 * @return the position of the ball in x-direction
	 */
	public double getBallPositionX() {
		return this.ballPositionX;
	}

	/**
	 * Get the position of the ball in y-direction
	 * @return the position of the ball in y-direction
	 */
	public double getBallPositionY() {
		return this.ballPositionY;
	}

	/**
	 * Get the speed of the ball in x-direction
	 * @return the speed of the ball in x-direction
	 */
	public double getBallDeltaX() {
		return this.ballDX;
	}

	/**
	 * Get the speed of the ball in y-direction
	 * @return the ball speed in y-direction
	 */
	public double getBallDeltaY() {
		return this.ballDY;
	}

	/**
	 * Set the speed of the ball in x-direction
	 */
	public void setBallDeltaX(double x) {
		this.ballDX = x;
	}

	/**
	 * Set the speed of the ball in y-direction
	 */
	public void setBallDeltaY(double y) {
		this.ballDY = y;
	}

	/**
	 * Set the position of the ball in x-direction
	 */
	public void setBallPositionX(double x) {
		this.ballPositionX = x;
	}

	/**
	 * Set the position of the ball in y-direction
	 */
	public void setBallPositionY(double y) {
		this.ballPositionY = y;
	}
	
	/**
	 * Get the current game score
	 * @return the score
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Reset the score to 0
	 */
	public void resetScore(){
		this.score = 0;
	}
	
	/**
	 * Increment the score by 1
	 */
	public void addScore(){
		
		this.score = this.score + 1 * multiplikator;
	}
	
	/**
	 * Get the current Level
	 * @return the Level
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * starts the next Level
	 */
	public void nextLevel(){
		level += 1;
		multiplikator += 1;
		lives += 1;
	}
	
	/**
	 * decrease the Live
	 */
	public void loseLive(){
		lives -= 1;
	}
	 /**
	  * gets the current amount of lives
	  * @return the amount of lives
	  */
	public int getLives(){
		return lives;
	}
	
	/**
	 * get the current multiplicator
	 * @return  the current multiplicator
	 */
	public int getMultiplikator(){
		return multiplikator;
	}
}
