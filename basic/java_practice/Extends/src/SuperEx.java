class Point {
	private int x, y;
	public Point() {
		this.x = this.y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void showPoint() {
		System.out.printf("(%d, %d)\n", this.x, this.y);
	}
}

class ColorPoint extends Point {
	private String color;
	public ColorPoint(int x, int y, String color) {
		super(x, y);	// Point税 持失切 Point(x, y) 持失
		this.color = color;
	}
	
	public void showColorPoint() {
		System.out.print(color);
		super.showPoint();
	}
}


public class SuperEx {
	public static void main(String[] args) {
		ColorPoint cp = new ColorPoint(5, 6, "blue");
		cp.showColorPoint();
	}
}
