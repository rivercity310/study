package BearGame;

public class Fish extends GameObject {
	public Fish(int x, int y, int distance) {
		super(x, y, distance); 
	}
	 
	@Override
	public void move() { // 한 번 움직이는 과정 전개
		int n = (int)(Math.random()*5); // 0,1,2,3,4 중에서 0인 경우 + 방향, 1인 경우 - 방향, 나머지 정지
		if(n==0) x += distance;
		else if(n==1) x -= distance;

		if(x < 0) x=0;
		if(x >= Game.MAX_X) x = Game.MAX_X - 1;
		 
		n = (int)(Math.random()*5);
		if(n==0) y += distance;
		else if(n==1) y -= distance;

		if(y < 0) y=0;
		if(y >= Game.MAX_Y) y= Game.MAX_Y - 1;
	}

	@Override
	public char getShape() { // Fish의 모양 리턴
		return '@';
	}
}
