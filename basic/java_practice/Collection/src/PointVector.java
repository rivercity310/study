import java.util.Vector;

class Point {
	private int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

public class PointVector {
	public static void main(String[] args) {
		Vector<Point> v = new Vector<Point>();
		Vector<Point> w = new Vector<Point>();
		
		v.add(new Point(2, 3));
		v.add(new Point(-5, 20));
		v.add(new Point(15, -8));
		
		w.addAll(v);
		for (int j = 0; j < w.size(); j++) {
			System.out.println(w.contains(v.get(j)));
			System.out.println(w.indexOf(v.get(j)));
		}
		
		while(!w.isEmpty()) {
			System.out.println(w.remove(w.size() - 1));
		}
			
		v.remove(1);
		for (int i = 0; i < v.size(); i++) 
			System.out.println(v.elementAt(i)); 	// ¶Ç´Â v.get(i)
		
		Object[] p = v.toArray();
		for (int i = 0; i < p.length; i++)
			System.out.println(p[i].toString());
	}
}
