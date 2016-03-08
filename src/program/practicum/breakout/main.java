package program.practicum.breakout;

public class main {
	public static void main(String[] args) {
		// BreakoutThread thread = new BreakoutThread();
		// thread.run();
		View view = new View();
		view.start();
		while (true) {
			view.controller.updateController();
			view.updateView();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {

			}
		}
	}
}
