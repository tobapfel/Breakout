package program.practicum.breakout;

import java.awt.Color;

import acm.graphics.GRect;

public class Bricks extends GRect {
	int row, col;

	/**
	 * builds a GRect with the given 2d - index
	 * 
	 * @param row
	 *            of the brick
	 * @param col
	 *            of the brick
	 */

	/**
	 * builds a GRect with the given 2d - index
	 * 
	 * @param row
	 *            of the brick
	 * @param col
	 *            of the bricks
	 * @param numberOfRow
	 *            total amount of rows
	 * @param numberOfCol
	 *            total amount of column
	 * @param windowHeight
	 *            height of the window
	 * @param windowWidth
	 *            width of the window
	 */
	public Bricks(int row, int col, double numberOfRow, double numberOfCol, double windowHeight, double windowWidth) {
		super(0, 0);

		this.setSize((windowWidth / numberOfCol) - (40 / numberOfCol), (windowHeight * 0.2) / numberOfRow);
		this.setLocation(this.getWidth() * col + 20, this.getHeight() * row + 20);
		this.row = row;
		this.col = col;
		this.setColor(Color.WHITE);

		switch (row % 8) {
		case 0:
		case 1:
			this.setFillColor(Color.RED);
			break;
		case 2:
		case 3:
			this.setFillColor(Color.ORANGE);
			break;
		case 4:
		case 5:
			this.setFillColor(Color.YELLOW);
			break;
		case 6:
		case 7:
			this.setFillColor(Color.ORANGE);
			break;
		}
		this.setFilled(true);
	}

	/**
	 * Get the row of this brick
	 * 
	 * @return the row
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Get the column of this brick
	 * 
	 * @return the column
	 */
	public int getCol() {
		return this.col;
	}
}
