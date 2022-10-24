package Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1110_2 {
    private static int N;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cycle(N);
        System.out.println(count);
        br.close();
    }

    public static void cycle(int n) {
        count++;

        int a = n % 10 * 10;
        int b = (n / 10 + n % 10) % 10;
        int num = a + b;

        if (num != N)
            cycle(num);
    }
}
