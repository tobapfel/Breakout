package program.practicum.breakout;

public class BreakoutThread implements Runnable {
	@Override
	public void run() {
		View view = new View();
		while (true) {
			view.updateView();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
