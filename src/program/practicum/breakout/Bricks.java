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
	public Bricks(int row, int col) {
		super(col * 10 + row * 2, 50 + row * 5 + (row - 1) * 2, 5, 10);
		this.row = row;
		this.col = col;

		switch (col % 8) {
		case 0:
		case 1:
			this.setFillColor(Color.RED);
		case 2:
		case 3:
			this.setFillColor(Color.ORANGE);
		case 4:
		case 5:
			this.setFillColor(Color.YELLOW);
		case 6:
		case 7:
			this.setFillColor(Color.ORANGE);
		}
		this.setFilled(true);
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}
}
