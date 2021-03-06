package program.practicum.breakout;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Settings extends HashMap<String, Integer>{
	/**
	 * Create a new SettingsMap.
	 * Load the keys and values from the file settings.txt
	 * which must be located in the same folder as the Breakout.jar
	 * this file can look like this:
	 * 
	 * # COMMENT
	 * FRAME_PAUSE = 7
	 * BALL_SPEED = 5
	 * ROWS = 6
	 * COLUMNS = 5
	`* LIVS = 10
	 * 
	 */
	public Settings(){
		// standard settings:
		this.put("WINDOW_WIDTH", 500);
		this.put("WINDOW_HEIGHT", 700);
		
		this.put("FRAME_PAUSE", 15);
		
		this.put("ROWS", 5);
		this.put("COLUMNS", 5);
		this.put("LIVES", 3);
		
		this.put("PADDLE_HEIGHT", 10);
		this.put("PADDLE_WIDTH", 100);
		
		this.put("BALL_SIZE", 10);
		this.put("BALL_SPEED", 3);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int displayWidth = gd.getDisplayMode().getWidth();
		int displayHeight = gd.getDisplayMode().getHeight();
		
		String curDir = ClassLoader.getSystemClassLoader().getResource(".").getPath();
		try (BufferedReader br = new BufferedReader(new FileReader(curDir+"settings.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if(line.startsWith("#")){
		    		// lines starting with '#' are comments 
		    		continue;
		    	}
		       String[] splitted = line.split("\\s=\\s");
		       this.put(splitted[0], Integer.parseInt(splitted[1]));
		    }
		    
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}