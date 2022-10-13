import java.util.Scanner;

public class StringCircle {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("´Ü¾î: ");
		String str = s.nextLine();
		
		for (int i = 0; i < str.length(); i++) {
			String remains = str.substring(1);
			char firstChar = str.charAt(0);
			
			String newStr = remains + Character.toString(firstChar);
			System.out.println(newStr);
			
			str = newStr;
		}
		
		s.close();
	}
}
