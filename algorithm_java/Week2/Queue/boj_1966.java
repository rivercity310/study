package Week2.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1966 {
    static StringTokenizer st;
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            Queue<Integer> q = new ArrayDeque<>();
            ArrayList<Integer> rank_list = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            while (n > 0) {
                st = new StringTokenizer(br.readLine());

                while (st.hasMoreTokens()) {
                    int rank = Integer.parseInt(st.nextToken());
                    q.add(rank);
                    rank_list.add(rank);
                }

                rank_list.sort(Collections.reverseOrder());

                int ans = 0;
                while (true) {
                    if (rank_list.indexOf(0) == q.peek()) {
                        int rv = rank_list.remove(0);
                        q.poll();
                        m--;

                        if (m == 0) {
                            ans = rv;
                            break;
                        }
                    }
                    else
                        q.add(q.poll());
                }

                sb.append(ans).append("\n");
                n--;
            }

            t--;
        }

        System.out.println(sb);
    }
}
