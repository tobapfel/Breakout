package program.practicum.breakout;

import acm.program.GraphicsProgram;

public class View extends GraphicsProgram {
	BreakoutModel model = new BreakoutModel(rows, columns, lives);

	@Override
	public void run() {
		setSize(500, 700);

	}

	/**
	 * removes a brick
	 * 
	 * @param brick
	 *            an object of type Bricks
	 */
	public void removeBricks(Bricks brick) {
		remove(brick);
		model.removeBrick(brick.getRow(), brick.getCol());
	}

}
