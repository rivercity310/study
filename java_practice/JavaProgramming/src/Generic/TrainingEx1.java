package Generic;

// Scanner 클래스로 -1이 입력될 때까지 양의 정수를 입력받아 벡터에 저장하고
// 벡터를 검색하여 가장 큰 수를 출력하는 프로그램 작성

import java.util.Scanner;
import java.util.Vector;

public class TrainingEx1 {
	public static void main(String[] args) {
		var s = new Scanner(System.in);
		var v = new Vector<Integer>();
		
		System.out.print("정수: ");
		while (true) {
			int input = s.nextInt();
			v.add(input);
			
			if (v.lastElement() == -1) {
				int max = 0;
				for (int i = 0; i < v.size(); i++)
					if (v.get(i) > max) max = v.get(i);
				
				System.out.printf("가장 큰 수는: %d", max);
				s.close();
				break;
			}
		} 
	}
}
