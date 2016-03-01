package program.practicum.breakout;

import acm.graphics.*;

public class BreakoutThread implements Runnable {
	public void run(){
		
		try{
			Thread.sleep(100);
		}
		catch(InterruptedException e){}
	}
}
