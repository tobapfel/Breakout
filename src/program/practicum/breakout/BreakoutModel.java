package program.practicum.breakout;

public class BreakoutModel {
	public boolean[][] bricks;
	private int lives;

	private double ballPositionX;
	private double ballPositionY;
	private double ballDX = 1;
	private double ballDY = 1;
	
	private int paddlePositionX;

	private int score;

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
	public BreakoutModel(int rows, int columns, int lives) {
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

		this.bricks = new boolean[rows][columns];
		this.lives = lives;
		this.score = 0;
	}

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
		this.bricks[row][column] = false;
	}

	public int getBrickRowCount() {
		return this.bricks.length;
	}

	public int getBrickColumnCount() {
		return this.bricks[0].length;
	}

	public void setPaddlePosition(double x) {
		this.setPaddlePosition((int) x);
	}

	public void setPaddlePosition(int x) {
		this.paddlePositionX = x;
	}

	public int getPaddlePosition() {
		return this.paddlePositionX;
	}

	public double getBallPositionX() {
		return this.ballPositionX;
	}
	public double getBallPositionY() {
		return this.ballPositionY;
	}

	public double getBallDeltaX() {
		return this.ballDX;
	}
	public double getBallDeltaY() {
		return this.ballDY;
	}
	public void setBallDeltaX(double x) {
		this.ballDX = x;
	}
	public void setBallDeltaY(double y) {
		this.ballDY = y;
	}
	

	public void setBallPositionX(double x) {
		this.ballPositionX = x;
	}
	public void setBallPositionY(double y) {
		this.ballPositionY = y;
	}

}
