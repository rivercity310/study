class A {
	private int x;
	public A() {
		System.out.println("持失切A");
	}
	
	public A(int x) {
		this.x = x;
	}
}

class B extends A {
	public B() {
		super(5);
		System.out.println("持失切B");
	}
}

class C extends B {
	public C() {
		System.out.println("持失切C");
	}
}

public class ConstructorEx {
	public static void main(String[] args) {
		C c = new C();
	}
}
