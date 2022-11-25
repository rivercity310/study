class Rect {
	private int width, height;
	public Rect(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	// 면적(w x h)이 같으면 두 Rect 객체가 같은 것으로 판단하는 equals() 메소드 오버라이딩 하기 
	public boolean equals(Object obj) {
		Rect r = (Rect)obj;
		if (width * height == r.width * r.height) return true;
		else return false;
	}
}

public class ObjectPropertyEx {
	public static void main(String[] args) {
		Rect a = new Rect(2, 5);
		Rect b = new Rect(1, 10);
		
		System.out.println(a.equals(b)); 	// true
	}	
}
