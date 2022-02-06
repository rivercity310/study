import java.util.Scanner;

class Dictionary {
	private static String kor[] = {"사랑", "아기", "돈", "미래", "희망"};
	private static String eng[] = {"love", "baby", "money", "future", "hope"};
	public static String kor2eng(String word) {
		for (int i = 0; i < kor.length; i++) 
			if (kor[i].equals(word))
				return eng[i];
		
		return null;
	}
}

public class DictApp {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void run() {
		while (true) {
			System.out.print("한글 단어?: ");
			String word = scanner.next();
			String answer = Dictionary.kor2eng(word);
			if (word.equals("그만")) {
				System.out.print("프로그램을 종료합니다...");
				scanner.close();
				return;
			}
				
			System.out.printf("%s는 %s\n", word, answer);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("한영 단어 프로그램입니다.");
		run();
	}
}
