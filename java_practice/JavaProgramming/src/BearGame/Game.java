package BearGame;

public class Game {
	public static final int MAX_X = 20;
	public static final int MAX_Y = 10;
	private char map [][] = new char [MAX_Y][MAX_X];
	private GameObject [] m = new GameObject[2];
	int state; // 0: 게임 중, 1: Monster가 winner, 2:Human이 winner
	
	public Game() {
		for(int i=0; i<MAX_Y; i++) 
			for(int j=0; j<MAX_X; j++)
				map[i][j] = '-';
		m[0] = new Bear(0, 0, 1);
		m[1] = new Fish(5, 5, 2);
		state = 0; // 게임 중
	}
	public void run() {
		System.out.println("** Bear의 Fish 먹기 게임을 시작합니다.**");;

		update(); // 초기 좌표에 따른 맵 설정
		draw(); // 초기 게임 맵을 보여줌

		while(!doesEnd()) {
			clear(); // 현재의 맵 지움
			for(int i=0; i<m.length; i++) 
				m[i].move();
			update(); // 움직인 후 좌표 변경에 따른 맵 갱신
			draw(); // 맵 그리기
		}
		System.out.println("Bear Wins!!");;
	}
	private void update() {
		for(int i=m.length-1; i>=0; i--) // Fish부터 먼저 그려서 Fish를 먹는 경우 Fish가 보이지 않기
			map[m[i].getY()][m[i].getX()] = m[i].getShape();
	}
	private void clear() {
		for(int i=0; i<m.length; i++) 
			map[m[i].getY()][m[i].getX()] = '-';
	}
	private void draw() {
		System.out.println();
		for(int i=0; i<MAX_Y; i++) {
			for(int j=0; j<MAX_X; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
	}
	private boolean doesEnd() {
		if(m[0].collide(m[1])) {// Bear ate Fish
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

}
