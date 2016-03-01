package program.practicum.breakout;

import java.awt.Color;
import acm.graphics.GRect;

public class Paddle extends GRect {
	
	/**
	 * The position of the paddle in the y-direction
	 */
	public final int YCOORDINATE = 100;
	/**
	 * A Paddle for playing breakout
	 * @param width The width of the paddle
	 * @param height The heigth of the paddle
	 */
	public Paddle(int width, int height){
		super(width, height);
		this.setFillColor(Color.BLACK);
		this.setFilled(true);
	}
}
