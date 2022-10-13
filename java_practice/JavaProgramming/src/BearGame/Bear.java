package BearGame;
import java.util.Scanner;

public class Bear extends GameObject {
	private Scanner scanner;
	public Bear(int x, int y, int distance) {
		super(x, y, distance);
		scanner = new Scanner(System.in);
	}
	
	@Override
	public void move() {
		System.out.print("왼쪽(a), 아래(s), 위(d), 오른쪽(f) >> ");
		char c;
		c = scanner.next().charAt(0);
		switch(c) {
			case 'a' : // left
				x--; 
				if(x < 0) x = 0;
				break;
			case 'f' : // right
				x++; 
				if(x >= Game.MAX_X) x = Game.MAX_X - 1;
				break;
			case 'd' : // up
				y--; 
				if(y < 0) y = 0;
				break;
			case 's' : // down
				y++; 
				if(y >= Game.MAX_Y) y = Game.MAX_Y - 1;
				break;
		}		
	}
	
	@Override
	public char getShape() { // Bear의 모양 리턴
		return 'B';
	}
}
