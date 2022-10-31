package Week1.OT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1110 {
    public static void main(String[] args) throws IOException {
        var s = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(s.readLine());
        int rst = n;
        int cnt = 1;

        while (true) {
            int a = rst % 10;
            int b = ((rst / 10) + (rst % 10)) % 10;
            rst = (a * 10) + b;

            if (rst == n) break;
            cnt++;
        }

        System.out.print(cnt);
        s.close();
    }
}
