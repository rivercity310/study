import java.util.Scanner;

public class hanoi {
	private static void hanoi_tower(int n, char from, char tmp, char to) {
		if (n == 1) System.out.printf("원반 1: %c => %c 이동\n", from, to);
		else {
			hanoi_tower(n - 1, from, to, tmp);
			System.out.printf("원반 %d: %c => %c 이동\n", n, from, to);
			hanoi_tower(n - 1, tmp, from, to);
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("구하려는 원반의 개수: ");
		int n = s.nextInt();
		
		System.out.printf("원반이 %d개 있을 때 하노이 탑의 결과\n\n", n);
		hanoi_tower(n, 'A', 'B', 'C');
		
		s.close();
	}
}
