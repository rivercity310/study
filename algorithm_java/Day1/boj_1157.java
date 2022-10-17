package Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1157 {
    public static void main(String[] args) throws IOException {
        var s = new BufferedReader(new InputStreamReader(System.in));
        String str = s.readLine();

        int[] cnt = new int[26];
        Arrays.fill(cnt, 0);

        for (int i = 0; i < str.length(); i++) {
            char c = Character.toUpperCase(str.charAt(i));
            cnt[c - 'A']++;
        }

        int max = Arrays.stream(cnt).max().getAsInt();
        int max_cnt = 0;
        int idx = -1;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == max) {
                max_cnt++;
                idx = i;
            }
        }

        if (max_cnt > 1) System.out.print("?");
        else System.out.print((char)(idx + 'A'));

        s.close();
    }
}
