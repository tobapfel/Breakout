package program.practicum.breakout;

import java.awt.Color;
import acm.graphics.GOval;

public class Ball extends GOval {
	/**
	 * A ball for playing Breakout
	 * @param size 
	 * 			size of the ball
	 */
	public Ball(int size){
		super(size, size);
		this.setFillColor(Color.BLACK);
		this.setFilled(true);
	}
}
