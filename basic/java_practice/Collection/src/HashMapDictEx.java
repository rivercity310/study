import java.util.*;

public class HashMapDictEx {
	public static void main(String[] args) {
		var s = new Scanner(System.in);
		var dict = new HashMap<String, String>();
		
		dict.put("baby", "아기");
		dict.put("apple", "사과");
		dict.put("love", "사랑");
		
		while(true) {
			System.out.print("찾고 싶은 단어? ");
			String eng = s.next();
			if (eng.equals("exit")) {
				System.out.println("프로그램을 종료합니다...");
				s.close();
				break;
			}
			
			// 해시맵의 전체 검색
			if (eng.equals("showAllWords")) {
				var keys = dict.keySet();		// Set<String> keys와 동일
				var it = keys.iterator();		// Iterator<String> it와 동일
				
				while (it.hasNext()) {
					String key = it.next();
					String value = dict.get(key);
					System.out.printf("(%s, %s)\n", key, value);
				}
				
				continue;
			}
			
			String kor = dict.get(eng);
			if (kor == null)
				System.out.println(eng + "는 없는 단어입니다.");
			else 
				System.out.println(kor);
		}
	}
}
