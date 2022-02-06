import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListEx {
	public static void main(String[] args) {
		var s = new Scanner(System.in);
		var a = new ArrayList<String>();
		
		for (int i = 0; i < 4; i++) {
			System.out.print("이름 >> ");
			String name = s.next();
			a.add(name);
		}
		
		for (int i = 0; i < a.size(); i++)
			System.out.println(a.get(i));
		
		
		// 가장 긴 이름 출력하기
		int longest = 0;
		for (int i = 1; i < a.size(); i++)
			if (a.get(longest).length() < a.get(i).length())
				longest = i;
		System.out.printf("가장 긴 이름은: %s", a.get(longest));
		
		s.close();
	}
}
