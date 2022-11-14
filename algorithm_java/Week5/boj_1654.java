package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1654 {
    static StringTokenizer st;
    static int k, n;
    static int max;
    static int min = 0;
    static int[] arr2;
    final static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr2 = new int[k];
        for (int i = 0; i < k; i++)
            arr2[i] = Integer.parseInt(br.readLine().trim());

        // 최소 N개를 만들 수 있는 랜선의 최대 길이는?
        max = Arrays.stream(arr2).max().getAsInt();

        System.out.println(bin());
    }

    static boolean a(int[] arr, int min) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] >= min)
                return true;

        return false;
    }
    static int bin() {
        int mid = -1;

        while (min < max) {
            int[] arr = arr2;
            mid = (min + max) / 2;

            int cnt = 0;

            while (true) {
                for (int i = 0; i < k; i++) {
                    if (arr[i] >= mid) {
                        cnt++;
                        arr[i] -= mid;
                    }
                }

                int c = 0;
                for (int i = 0; i < k; i++) {
                    if (arr[i] < mid) {
                        c++;
                    }
                }

                if (c == arr.length)
                    break;
            }

            if (cnt < n) max = mid - 1;
            else if (cnt > n) min = mid + 1;
        }

        return mid;
    }
}
