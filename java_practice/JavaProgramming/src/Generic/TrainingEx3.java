package Generic;

// "그만"이 입력될 때까지 나라 이름과 인구를 입력받아 저장하곡
// 다시 나라 이름을 입력하면 해당 나라의 인구를 출력하라

import java.util.Scanner;
import java.util.HashMap;

public class TrainingEx3 {
	public static void main(String[] args) {
		var h = new HashMap<String, Integer>();
		var s = new Scanner(System.in);
		
		System.out.println("나라 이름과 인구를 입력하세요");
		while (true) {
			System.out.print("나라 이름, 인구: ");
			String country = s.next();
			if (country.equals("그만")) break;
			int population = s.nextInt();
			h.put(country.toUpperCase(), population);
		}
		
		while (true) {
			try {
				System.out.print("인구 검색: ");
				String country = s.next();
				if (country.equals("그만")) {
					s.close();
					return;
				}
				
				int pop = h.get(country.toUpperCase());
				System.out.printf("%s의 인구는 %d\n", country, pop);
			} catch(NullPointerException e) {
				System.out.println("해당 국가는 등록되어 있지 않습니다.");
			}
		}
	}
}
