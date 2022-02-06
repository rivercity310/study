package Generic;

import java.util.*;

class Word {
	private String kor;
	private String eng;
	
	public Word(String k, String e) {
		this.kor = k;
		this.eng = e;
	}
	
	public String getKor() {
		return this.kor;
	}
	
	public String getEng() {
		return this.eng;
	}
}


public class EngWordGameEx {
	private Vector<Word> v;
	private Scanner s;
	private Random r;
	private int count;
	
	public EngWordGameEx() {
		s = new Scanner(System.in);
		r = new Random();
		v = new Vector<>();
		count = 0;
		
		initVector();
	}
	
	private void initVector() {
		v.add(new Word("사랑", "love"));
		v.add(new Word("행복", "happy"));
		v.add(new Word("친구", "friend"));
		v.add(new Word("아기", "baby"));
		v.add(new Word("연필", "pencil"));
		v.add(new Word("소고기", "beef"));
		v.add(new Word("일본", "japan"));
		v.add(new Word("한국", "korea"));
		v.add(new Word("큐브", "cube"));
		v.add(new Word("향수", "nostalgia"));
	}
	
	private void run() {
		System.out.println("명품영어 단어 테스트를 시작합니다. -1을 입력하면 종료합니다.");
		System.out.printf("현재 %d개의 단어가 들어있습니다.\n", v.size());
		
		for (int i = 0; i < v.size(); i++) {
			int first, second, third;
			while (true) {
				first = r.nextInt(v.size());
				second = r.nextInt(v.size());
				third = r.nextInt(v.size());
				
				if (i == first || i == second || i == third) continue;
				if (first != second && second != third && first != third) break;
			}

			int[] idxAry = {i, first, second, third};
			idxAry = shuffleAry(idxAry);
			
			System.out.printf("%s?\n", v.get(i).getEng());
			System.out.printf("(1)%s (2)%s (3)%s (4)%s : ", v.get(idxAry[0]).getKor(), v.get(idxAry[1]).getKor(), v.get(idxAry[2]).getKor(), v.get(idxAry[3]).getKor());
			int answer = s.nextInt();
			
			if (answer == -1) {
				System.out.print("명품영어를 종료합니다.\n");
				s.close();
				break;
			}
			
			if (v.get(i).equals(v.get(idxAry[answer - 1]))) {
				System.out.println("Excellent!!\n");
				count++;
			}
			else System.out.println("No!!\n");
		}
		
		System.out.printf("성적: %d/%d\n", count, v.size());
	}
	
	private int[] shuffleAry(int[] a) {
		// System.out.print("초기 배열: ");
		// for (int i = 0; i < a.length; i++)
		//  	System.out.print(a[i] + " ");
		
		for (int x = 0; x < a.length; x++) {
			int i = r.nextInt(a.length);
			int j = r.nextInt(a.length);
			
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		
		// System.out.print("셔플 후 배열: ");
		// for (int i = 0; i < a.length; i++) 
		//  	System.out.print(a[i] + " ");
		// System.out.println();
		
		return a;
	}
	
	public static void main(String[] args) {
		var app = new EngWordGameEx();
		app.run();
	}
}