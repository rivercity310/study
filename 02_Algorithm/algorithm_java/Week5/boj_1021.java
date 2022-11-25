package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1021 {
    static StringTokenizer st;
    static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        int idx = 0;
        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens())
            arr[idx++] = Integer.parseInt(st.nextToken());

        // 3 4 5 6 7 8  7 6 5

    }
}
