package program.practicum.breakout;

import java.awt.Color;
import acm.graphics.GOval;

public class Ball extends GOval {
	public Ball(int size){
		super(size, size);
		this.setFillColor(Color.BLACK);
		this.setFilled(true);
	}
}
