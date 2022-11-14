package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2805 {
    static int N, M;
    static int[] arr;
    static int max = 0;

    static StringTokenizer st;
    final static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        max = Arrays.stream(arr).max().getAsInt();
        System.out.print(bin());
    }

    static int bin() {
        int mid;
        int min = 0;

        while (min < max) {
            mid = (min + max) / 2;

            long dist = 0;
            for (int num : arr)
                dist += num - mid >= 0 ? num - mid : 0;

            if (dist > M) min = mid + 1;
            else if (dist < M) max = mid - 1;
            else break;
        }

        return min;
    }
}
