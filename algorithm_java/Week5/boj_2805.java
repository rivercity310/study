package Week5;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2805 {

    static int N;
    static int M;
    static long max = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        max = Arrays.stream(arr).max().getAsInt();
        max++;

        System.out.println(binary());
    }
    static long binary() {
        long mid;
        long min = 0;

        while (min < max) {
            mid = (min + max) / 2;

            long dist = 0;
            for (int num : arr) {
                dist += num - mid >= 0 ? num - mid : 0;
            }

            if (dist > M) min = mid + 1;
            else if (dist < M) {
                max = mid;
            } else {
                min = mid + 1;
                break;
            }
        }

        return min - 1;
    }
}