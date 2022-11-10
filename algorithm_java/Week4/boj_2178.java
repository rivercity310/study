package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2178 {
    static StringTokenizer st;
    static int[][] arr;

    static final int size = 4;
    static final int[] rows = { -1, 1, 0, 0 };
    static final int[] cols = { 0, 0, -1, 1 };
    static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int curR = tmp[0];
            int curC = tmp[1];

            for (int i = 0; i < size; i++) {
                int nextR = curR + rows[i];
                int nextC = curC + cols[i];

                if (nextR < 0 || nextC < 0 || nextR >= arr.length || nextC >= arr[nextR].length)
                    continue;

                if (arr[nextR][nextC] == 0)
                    continue;

                if (arr[nextR][nextC] == 1) {
                    arr[nextR][nextC] = arr[curR][curC] + 1;
                    q.add(new int[]{ nextR, nextC });
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();

            for (int j = 0; j < chars.length; j++)
                arr[i][j] = chars[j] - '0';

        }

        bfs(0, 0);
        System.out.println(arr[n - 1][m - 1]);
    }
}
