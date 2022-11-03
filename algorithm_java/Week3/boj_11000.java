package Week3;

// 강의실 배정


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_11000 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int table[][];

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        table = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(table, ((o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        }));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(table[0][1]);

        for (int i = 1; i < n; i++) {
            if (table[i][0] >= pq.peek()) pq.poll();
            pq.add(table[i][1]);
        }

        System.out.println(pq.size());
        br.close();
    }
}
