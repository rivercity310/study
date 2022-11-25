package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2667_bfs {
    static int[][] arr;
    static int cnt = 0;

    static final StringBuilder sb = new StringBuilder();
    static final List<Integer> ans = new ArrayList<>();
    static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    // 사방탐색 : 상, 하, 좌, 우를 나타내는 벡터
    static final int[] rows = { -1, 1, 0, 0 };
    static final int[] cols = { 0, 0, -1, 1 };

    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        arr[r][c] = 0;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int curR = tmp[0];
            int curC = tmp[1];

            cnt++;

            for (int i = 0; i < 4; i++) {
                int nextR = curR + rows[i];
                int nextC = curC + cols[i];

                if (nextR < 0 || nextC < 0 || nextR >= arr.length || nextC >= arr.length || arr[nextR][nextC] == 0)
                    continue;

                q.add(new int[]{nextR, nextC});
                arr[nextR][nextC] = 0;
            }
        }

        ans.add(cnt);
    }

    public static void main(String[] args) throws IOException {

        // 입력
        int n = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();     // {'0', '1', '0', ... }

            for (int j = 0; j < chars.length; j++)
                arr[i][j] = chars[j] - '0';
        }


        // 로직...
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (arr[i][j] == 1) {
                    bfs(i, j);
                    cnt = 0;
                }

        Collections.sort(ans);

        // 출력
        sb.append(ans.size()).append("\n");
        for (int val : ans)
            sb.append(val).append("\n");

        System.out.print(sb);
        br.close();
    }
}
