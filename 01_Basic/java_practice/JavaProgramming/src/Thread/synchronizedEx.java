package Thread;

/*
 * 공유 데이터인 집계판을 시뮬레이션하는 클래스
 * 두 StudentThread 스레드에 의해 동시 접근됨
 */
class SharedBoard {
	private int sum = 0;   // 집계판의 합
	
	public void add() {
		int n = sum;
		Thread.yield();   // 현재 실행중인 스레드 양보
		n += 10;
		sum = n;
		System.out.println(Thread.currentThread().getName() + " : " + sum);
	}
	
	public int getSum() {return sum;}
}

// 학생을 시뮬레이션하는 스레드 클래스
class StudentThread extends Thread {
	private SharedBoard board;    // 집계판의 주소
	
	public StudentThread(String name, SharedBoard board) {
		super(name);
		this.board = board;
	}
	
	// 집계판에 10번 접근하여 카운팅한다.
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) board.add();
	}
}

public class synchronizedEx {
	public static void main(String[] args) {
		SharedBoard board = new SharedBoard();   // 집계판 공유 데이터 생성
		
		// 스레드 생성시 집계판의 주소를 알려준다. 두 스레드는 집계판에 동시에 접근한다.
		Thread th1 = new StudentThread("승수", board);    // "승수"라는 이름의 스레드 생성
		Thread th2 = new StudentThread("수연", board);    // "수연"이라는 이름의 스레드 생성
		
		// 두 스레드 실행
		th1.start();
		th2.start();
	}
}
