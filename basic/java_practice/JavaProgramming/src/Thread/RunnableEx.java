package Thread;

import java.lang.Runnable;

class TimerRunnable implements Runnable {
	private int n = 0;
	
	@Override
	public void run() {
		while (true) {
			System.out.println(n++);
			try {
				// Runnable 인터페이스에는 sleep이 없지만, Thread 클래스의 static이므로 다음과 같이 사용가능
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			}
		}
	}
}

public class RunnableEx {
	public static void main(String[] args) {
		var th = new Thread(new TimerRunnable());
		th.start();
	}
}
