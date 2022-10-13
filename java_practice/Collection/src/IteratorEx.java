import java.util.*;

public class IteratorEx {
	public static void main(String[] args) {
		var v = new Vector<Integer>();
		v.add(5);
		v.add(4);
		v.add(Integer.valueOf(-1));
		v.add(Integer.valueOf(-5));
		
		// Iterator를 이용한 모든 정수 출력하기
		// v.iterator()는 벡터 v의 요소를 순차 검색하게 해주는 Iterator 객체를 리턴한다.
		var it = v.iterator();		// Iterator<Integer> 또는 var
		while (it.hasNext()) {
			int n = it.next();
			System.out.println(n);
			it.remove();
		}
		
		
		// it.remove()에 의해 제거되어 벡터에는 아무것도 남지 않음
		for (int i = 0; i < v.size(); i++) 
			System.out.printf("v[%d]: %d", i, v.get(i));
	}
}
