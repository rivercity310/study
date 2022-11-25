package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2606 {
    private final static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    private static StringTokenizer st;
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        bfs(1);
        System.out.println(cnt - 1);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            cnt++;

            for (int k : graph[now]) {
                if (!visited[k]) {
                    q.add(k);
                    visited[k] = true;
                }
            }
        }
    }
}
