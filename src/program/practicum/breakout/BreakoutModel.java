package program.practicum.breakout;

public class BreakoutModel {
	private boolean[][] bricks;
	private int lives;

	private int ballPosition;
	private int paddlePosition;
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

	/**
	 * Remove the Brick at the given coordinate
	 * 
	 * @param row
	 *            [int] the row of the brick
	 * @param column
	 *            [int] the column of the brick
	 */

	public void removeBrick(int row, int column) {
		this.bricks[row][column] = false;
	}

}
