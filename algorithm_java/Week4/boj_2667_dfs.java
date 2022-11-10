package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2667_dfs {
    static final StringBuilder sb = new StringBuilder();
    static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    static final List<Integer> ans = new ArrayList<>();
    static int cnt = 0;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < n; j++)
                arr[i][j] = chars[j] - '0';
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j)) {
                    ans.add(cnt);
                    cnt = 0;
                }
            }
        }

        Collections.sort(ans);

        sb.append(ans.size()).append("\n");
        for (int i = 0; i < ans.size(); i++)
            sb.append(ans.get(i)).append("\n");

        System.out.print(sb);
    }

    private static boolean dfs(int r, int c) {
        if (r < 0 || r >= arr.length || c < 0 || c >= arr[r].length)
            return false;

        if (arr[r][c] == 1) {
            arr[r][c] = 0;
            cnt++;

            dfs(r + 1, c);
            dfs(r - 1, c);
            dfs(r, c + 1);
            dfs(r, c - 1);

            return true;
        }

        return false;
    }
}
