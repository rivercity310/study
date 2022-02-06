
public class WrapperEx {
	public static void main(String[] args) {
		// toLowerCase('A'): 'A'를 소문자로 변환
		System.out.println(Character.toLowerCase('A'));		// a
		
		char c1 = '4', c2 = 'F';
		
		// isDigit: 문자 c1이 숫자이면 true
		if (Character.isDigit(c1))	
			System.out.println(c1 + "은/는 숫자");
		
		// isAlphabetic: 문자 c2가 영문자이면 true
		if (Character.isAlphabetic(c2))
			System.out.println(c2 + "는 영문자");
		
		
		
		System.out.println(Integer.parseInt("-123"));
		System.out.println(Integer.toHexString(28));		// 28을 16진수 문자열로 변환
		System.out.println(Integer.toBinaryString(28));		// 28을 2진수 문자열로 변환
		System.out.println(Integer.bitCount(28));			// 28에 대한 2진수의 1의 개수
		
		
		Double d = Double.valueOf(3.14);  					// 또는 Double d = 3.14; (자동 박싱)
		System.out.println(d.toString()); 					// Double을 문자열 "3.14"로 변환
		System.out.println(Double.parseDouble("3.14"));		// 문자열을 실수 3.14로 변환
		
		
		boolean b = (4 > 3);								// b는 true
		System.out.println(Boolean.toString(b));	  		// true를 문자열 "true"로 변환
		System.out.println(Boolean.parseBoolean("false"));	// 문자열을 false로 변환
	}
}
