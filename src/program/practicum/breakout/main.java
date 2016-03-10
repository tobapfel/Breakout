package program.practicum.breakout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class main {
	public static void main(String[] args) {
		
		// A map to store the settings
		Settings settings = new Settings();
		
		View view = new View(settings);
		//start the window
		view.start();
		
		//update loop
		while (true) {
			view.controller.updateController();
			view.updateView();

			try {
				Thread.sleep(settings.get("FRAME_PAUSE"));
			} catch (InterruptedException e) {

			}
		}
	}
}
