package Thread;

import java.lang.Thread;
import java.awt.*;
import javax.swing.*;

class TimerThread extends Thread {
	private JLabel timerLabel;   // 타이머 값이 출력될 레이블
	private int n = 0;
	
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	
	// Thread의 run() 메소드를 오버라이딩한다. run 메소드에 작성된 코드를 스레드 코드라고 부른다.
	@Override
	public void run() {
		while (true) {
			timerLabel.setText(Integer.toString(n++));;
			try {
				sleep(1000);
			} catch(InterruptedException e) {
				return;    // 예외가 발생하면 스레드 종료
			}
		}
	}
}

public class ThreadEx extends JFrame {
	public ThreadEx() {
		setTitle("Thread를 상속받은 타이머 스레드 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 타이머 값을 출력할 레이블 생성
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);
		
		// 타이머 스레드 객체 생성, 타이머 값을 출력할 레이블 생성자에 전달
		TimerThread th = new TimerThread(timerLabel);
		
		setSize(300, 170);
		setVisible(true);
		
		th.start();
	}
	
	public static void main(String[] args) {
		new ThreadEx();
	}
}
