package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1260 {
    private static StringTokenizer st;
    private static final StringBuilder sb = new StringBuilder();
    private static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    private static int[][] graph;
    private static boolean[] visited;

    /* 재귀 or 스택 */
    private static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(" ");

        for (int i = 1; i < graph.length; i++)
            if (graph[start][i] == 1 && !visited[i])
                dfs(i);
    }

    /* 큐 */
    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for (int i = 1; i < graph.length; i++) {
                if (graph[now][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        dfs(v);
        sb.append("\n");
        visited = new boolean[n + 1];
        bfs(v);

        System.out.println(sb);
    }
}
