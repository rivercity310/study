abstract class OddDetector {
	protected int n;
	public OddDetector(int n) {
		System.out.println("super");
		this.n = n;
	}
	public abstract boolean isOdd();
}

public class PencilEx extends OddDetector {
	public PencilEx(int n) {
		super(n);
		System.out.println("sub");
	}
	
	public boolean isOdd() {
		return super.n % 2 != 0;
	}
	
	public static void main(String[] args) {
		PencilEx p = new PencilEx(5);
		System.out.println(p.isOdd());
	}
}