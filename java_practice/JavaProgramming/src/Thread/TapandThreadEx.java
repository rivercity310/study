package Thread;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyLabel extends JLabel {
	private int barSize = 0;  // 그려질 바의 크기
	private int maxBarSize;   // 바의 최대 크기
	
	public MyLabel(int maxBarSize) {
		this.maxBarSize = maxBarSize;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.MAGENTA);
		int width = (int) (((double)(this.getWidth())) / maxBarSize * barSize);
		
		if (width == 0) return;   // 크기가 0이므로 바를 그릴 필요 없음
		g.fillRect(0, 0, width, this.getHeight());
	}
	
	synchronized public void fill() {
		if (barSize == maxBarSize) {
			try {
				wait();      // 바가 최대이면, ConsumerThread에 의해 바가 줄어들 때까지 대기
			} catch(InterruptedException e) {
				return;
			}
		}
		
		barSize++;
		repaint();  // 바의 크기가 변했으니 다시 그리기
		notify();   // 기다리는 ConsumerThread 스레드 깨우기
	}
	
	synchronized public void consume() {
		if (barSize == 0) {
			try {
				wait(); // 바의 크기가 0이면 바의 크기가 0보다 커질 때까지 대기
			} catch (InterruptedException e) {
				return;
			}
		}
		
		barSize--;
		repaint();
		notify();  // 기다리는 이벤트 스레드 깨우기
	}
}

class ConsumerThread extends Thread {
	private MyLabel bar;
	
	public ConsumerThread(MyLabel bar) {
		this.bar = bar;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				sleep(200);
				bar.consume();   // 0.2초마다 바를 1씩 줄인다.
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}

public class TapandThreadEx extends JFrame {
	private MyLabel bar = new MyLabel(100);       // 바의 최대 크기를 100으로 설정
	
	public TapandThreadEx(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		bar.setBackground(Color.ORANGE);
		bar.setOpaque(true);
		bar.setLocation(20, 50);
		bar.setSize(300, 20);
		c.add(bar);
		
		
		c.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				bar.fill();   // 키를 누를 때마다 바가 1씩 증가한다.
			}
		});
		
		setSize(350, 200);
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus();
		ConsumerThread th = new ConsumerThread(bar);
		th.start();
	}
	
	public static void main(String[] args) {
		new TapandThreadEx("아무 키나 빨리 눌러 바를 채워!");
	}
}
