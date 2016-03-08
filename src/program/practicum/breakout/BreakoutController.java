package program.practicum.breakout;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;

public class BreakoutController {

	public BreakoutModel model;
	public View view;
	
	public BreakoutController(View view){
		this.view = view;
		this.model = view.model;
	}
	/*
	public void mouseMoved(MouseEvent event) {
		this.model.setPaddlePosition(event.getX());
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("CLICK!");
	}
	*/
	public void ballMovement(){
		this.model.setBallPositionX(this.model.getBallPositionX() + this.model.getBallDeltaX());
		this.model.setBallPositionY(this.model.getBallPositionY() + this.model.getBallDeltaY());
	}
	
	public void ballCollision(){
		
	}

	public void updateController() {
		// move the paddle
		// getMousePosition returns null, if the cursor is outside the window
		// in that case we just leave the paddle where it is
		Point mousePos = this.view.getGCanvas().getMousePosition();
		if(mousePos != null){
			this.model.setPaddlePosition(mousePos.getX());
		}
		// check for collisions and move the ball
		ballCollision();
		ballMovement();
		
		System.out.println(this.model.getBallPositionX() + "," + this.model.getBallPositionY());
		
		// break the bricks
		
		
	}
}
