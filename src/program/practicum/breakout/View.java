package program.practicum.breakout;

import acm.program.GraphicsProgram;

public class View extends GraphicsProgram {
	BreakoutModel model = new BreakoutModel(rows, columns, lives);

	@Override
	public void run() {
		setSize(500, 700);
		BreakoutController controller = new BreakoutController(this.model);
		Bricks[][] bricks = new Bricks[1][1]; // get methoden fehlen

	}

	/**
	 * removes a brick
	 * 
	 * @param brick
	 *            an object of type Bricks
	 */

	public void updateView() {
		removeAll();

	}

}
