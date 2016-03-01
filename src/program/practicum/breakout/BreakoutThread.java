package program.practicum.breakout;

public class BreakoutThread implements Runnable {
	@Override
	public void run() {
		View view = new View();
		while (true) {
			view.updateView();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}
