package Thread;

public class ThreadInterrupt {
	public static void main(String[] args) {
		Thread th = new Thread(new TimerRunnable());
		th.start();
		
		try {
			Thread.sleep(3000);
			th.interrupt();
		} catch (InterruptedException e) {
			return;
		}
	}
}
