import java.util.Scanner;

class Add {
	private int a, b;
	public void setValue(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int calculator() {
		return this.a + this.b;
	}
}

class Sub {
	private int a, b;
	public void setValue(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int calculator() {
		return this.a - this.b;
	}
}

class Mul {
	private int a, b;
	public void setValue(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int calculator() {
		return this.a * this.b;
	}
}

class Div {
	private int a, b;
	public void setValue(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int calculator() {
		return this.a / this.b;
	}
}
	

public class Calculator {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {	
			System.out.print("두 정수와 연산자를 입력하세요: ");
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			String operator = scanner.next();
			
			if (operator.equals("stop")) {
				System.out.print("프로그램을 종료합니다...");
				scanner.close();
				return;
			}
			
			switch(operator) {
			case "+":
				Add add = new Add();
				add.setValue(a, b);
				System.out.println(add.calculator());
				break;
			case "-":
				Sub sub = new Sub();
				sub.setValue(a, b);
				System.out.println(sub.calculator());
				break;
			case "*":
				Mul mul = new Mul();
				mul.setValue(a, b);
				System.out.println(mul.calculator());
				break;
			case "/":
				Div div = new Div();
				div.setValue(a, b);
				System.out.println(div.calculator());
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}		
	}
}


