import java.util.Scanner;

class Phone {
	private String name;
	private String tel;
	
	public Phone(String name, String tel) {
		this.name = name;
		this.tel = tel;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getTel() {
		return this.tel;
	}
}

public class PhoneBook {
	private static Phone[] phone;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void init() {
		System.out.print("인원 수: ");
		int num = scanner.nextInt();
		phone = new Phone[num];
		for (int i = 0; i < phone.length; i++) {
			System.out.print("이름과 전화번호를 입력하세요 (띄워쓰기로 구분): ");
			String name = scanner.next();
			String tel = scanner.next();
			phone[i] = new Phone(name, tel);
		}
		
		System.out.println("성공적으로 저장되었습니다....\n");
	}
	
	public static String search(String name) {
		for (int i = 0; i < phone.length; i++) 
			if (phone[i].getName().equals(name)) {
				return phone[i].getTel();
			}
		return null;
	}
	
	public static void main(String[] args) {
		init();
		
		while (true) {
			System.out.print("검색할 이름: ");
			String name = scanner.next();
			if (name.equals("그만"))
				break;
			 
			System.out.printf("%s의 전화번호는 %s입니다.\n", name, search(name));
		}
	}
}
