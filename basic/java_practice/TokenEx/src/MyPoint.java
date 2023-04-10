
public class MyPoint {
	private int x, y;
	
	public MyPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object obj) {
		MyPoint p = (MyPoint)obj;	// 다운캐스팅
		return this.x == p.x && this.y == p.y;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")에 있는 점입니다."; 
	}
	
	public static void main(String[] args) {
		MyPoint p = new MyPoint(3, 50);
		MyPoint q = new MyPoint(4, 50);
		
		if (p.equals(q))
			System.out.println("같은 점");
		else 
			System.out.println("다른 점");
		
		System.out.println(p);
		System.out.println(q);
	}
}
