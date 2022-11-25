class Shape {
	protected int x;
	protected int y;
	
	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double getArea() {
		return this.x * this.y;
	}
}

class Rect extends Shape {
	private int width;
	private int height;
	public Rect(int x, int y, int w, int h) {
		super(x, y);
		this.width = w;
		this.height = h;
	}
	
	@Override
	public double getArea() {
		return this.width * this.height;
	}
}

class Circle extends Shape {
	private int radius;
	
	public Circle(int x, int y, int r) {
		super(x, y);
		this.radius = r;
	}
	
	@Override
	public double getArea() {
		return Math.PI * Math.pow(this.radius, 2);
	}
}


public class OverridingEx {
	public static void main(String[] args) {
		Rect r = new Rect(10, 10, 5, 5);
		System.out.println(r.getArea());
		
		Circle c = new Circle(10, 10, 3);
		System.out.println(Math.round(c.getArea() * 10) / 10.0);
		
		
		// 업캐스팅 시 서브클래스의 getArea()가 실행됨
		Shape s = new Rect(5, 5, 10, 10);
		System.out.println(s.getArea());
	}
}
