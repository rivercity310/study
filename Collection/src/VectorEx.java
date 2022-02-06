import java.util.Vector;

public class VectorEx {
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		v.add(Integer.valueOf(5));
		v.add(Integer.valueOf(4));
		v.add(-1);	// 자동 박싱에 의해 int 타입 -> Integer 객체로 변환되어 삽입
		v.add(2, 100);	// index 2에 100 삽입
		
		
		
		
		// 벡터 내의 요소 가져오기
		Integer obj = v.get(1);
		int i = obj.intValue();
		int j = v.get(2);	// 자동 언박싱
		System.out.printf("i: %d\n", i);
		System.out.printf("j: %d\n", j);
		for (int k = 0; k < v.size(); k++)
			System.out.printf("v[%d]: %d\n", k, v.get(k));
		
		// 벡터 속 모든 정수 더하기
		int sum = 0;
		for (int l = 0; l < v.size(); l++) {
			sum += v.elementAt(l);
		}
		System.out.println("모든 정수의 합: " + sum);
		
		
		
		
		// 벡터의 크기와 용량
		int len = v.size(); // 벡터 내 요소 객체의 수
		int cap = v.capacity();	// 벡터의 용량: 기본 크기 10
		System.out.printf("len: %d\n", len);	
		System.out.printf("cap: %d\n", cap);	
		
		
		
		
		// 벡터에서 요소 삭제: 삭제된 후 뒤에 있는 모든 요소가 앞으로 한자리씩 이동한다.
		// 마지막 요소 얻어내기
		System.out.println(v.lastElement());
		
		// pop
		while (v.size() > 0) {
			System.out.println(v.remove(v.size() - 1));
			
		}
		
		// 모든 요소 삭제
		v.removeAllElements();
		
	}
}
