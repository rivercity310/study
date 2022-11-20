package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2740 {
    static StringTokenizer st;
    static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    private static int[][] input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] tmp = new int[n][m];

        for (int j = 0; j < n; j++) {
            st = new StringTokenizer(br.readLine());
            int idx = 0;

            while (st.hasMoreTokens()) {
                tmp[j][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        return tmp;
    }

    public static void main(String[] args) throws IOException {
        int[][] a = input();
        int[][] b = input();

        int an = a.length;
        int am = a[0].length;
        int bn = b.length;
        int bm = b[0].length;

        int[][] rst = new int[an][bm];
        for (int i = 0; i < an; i++)
            for (int j = 0; j < bm; j++)
                rst[i][j] = 0;

        for (int k = 0; k < an; k++) {

            int cols = 0;

            for (int i = 0; i < am; i++) {
                for (int j = 0; j < bn; j++)
                    rst[k][cols] += a[i][j] * b[j][i];

                cols++;
            }
        }

        for (int i = 0; i < an; i++) {
            for (int j = 0; j < bm; j++)
                System.out.print(rst[i][j] + " ");
            System.out.println();
        }
    }

    /*
    1 2
    3 4   *  -1 -2 0
    5 6       0  0 3
     */
}
