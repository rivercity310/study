package Generic;

// Vector 컬렉션을 이용하여 강수량의 평균을 유지 관리하는 프로그램 작성
// 강수량을 입력하면 벡터에 추가하고 현재 입력된 모든 강수량과 평균을 출력한다.

import java.util.Scanner;
import java.util.Vector;

public class TrainingEx4 {
	public static void main(String[] args) {
		var s = new Scanner(System.in);
		var v = new Vector<Integer>();
		
		while (true) {
			System.out.print("강수량 입력: ");
			int rain = s.nextInt();
			if (rain == 0) {
				s.close();
				return;
			}
			v.add(rain);
			
			double aver = 0;
			for (int i = 0; i < v.size(); i++) {
				aver += v.get(i);
				System.out.print(v.get(i) + " ");
			}
			System.out.println();
			System.out.printf("현재 평균: %.2f\n\n", aver/v.size());
		}
	}
}
