package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1012 {
    private static StringTokenizer st;
    private static int[][] graph;
    private final static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    private static boolean dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= graph.length || y >= graph[x].length)
            return false;

        if (graph[x][y] == 1) {
            graph[x][y] = 0;

            dfs(x + 1, y);
            dfs(x - 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);

            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            st = new StringTokenizer(br.readLine());

            int cols = Integer.parseInt(st.nextToken());
            int rows = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            graph = new int[rows + 1][cols + 1];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[y][x] = 1;
            }

            int cnt = 0;

            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    if (dfs(i, j))
                        cnt++;

            System.out.println(cnt);
            t--;
        }
    }
}
