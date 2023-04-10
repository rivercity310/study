import java.util.Scanner;

public class JSP {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름: ");
		String name = scanner.next();
		System.out.println("이름은: " + name);
		
		scanner.close();
	}
}
