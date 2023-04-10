import java.util.StringTokenizer;


public class TokenizerEx {
	public static void main(String[] args) {
		// 문자열 분리는 StringTokenizer 객체 또는 String 객체의 split 메서드로 할 수 있다.
		
		// 1. StringTokenizer 객체를 이용해 문자열을 분리하고, 문자열 타입의 숫자를 정수로 변환해 합을 구하기
		String s = "a=3,b=5,c=6, d = 56, e = 10, f = 12";
		StringTokenizer st = new StringTokenizer(s, ",=");
		
		int sum = 0;
		while (st.hasMoreTokens()) {
			String nextToken = st.nextToken().trim();
			if (isStringInt(nextToken)) 
				sum += Integer.parseInt(nextToken);
		}
		
		System.out.println("합은 " + sum);		
		
		int sum2 = 0;
		String[] sp = s.split(",");
		for (int i = 0; i < sp.length; i++) {
			sp[i] = sp[i].trim();
			String[] spAry = sp[i].split("=");
			for (int j = 0; j < spAry.length; j++) {
				spAry[j] = spAry[j].trim();
				if (isStringInt(spAry[j])) {
					sum2 += Integer.parseInt(spAry[j]);
				}
			}
		}
		
		System.out.println("합은 " + sum2);
	}
	
	private static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
