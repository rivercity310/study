package Generic;

// Scanner 클래스를 사용하여 6개의 학점을 문자로 입력받아 ArrayList에 저장하고
// ArrayList를 검색하여 학점을 점수로 변환하여 평균을 출력하기

import java.util.Scanner;
import java.util.ArrayList;

public class TrainingEx2 {
	public static void main(String[] args) {
		var s = new Scanner(System.in);
		var a = new ArrayList<String>();
		
		System.out.print("6개의 학점 입력: ");
		for (int i = 0; i < 6; i++) {
			String grade = s.next();
			a.add(i, grade.toUpperCase());
		}
		
		double sum = 0;
		for (String k : a) {
			switch (k) {
			case "A": sum += 4.0; break;
			case "B": sum += 3.0; break;
			case "C": sum += 2.0; break;
			case "D": sum += 1.0; break;
			case "F": break;
			}
		}
		
		System.out.printf("평균 학점: %.2f", sum / a.size());
		s.close();	
	}
}