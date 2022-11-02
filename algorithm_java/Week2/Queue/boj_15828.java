package Week2.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class boj_15828 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<Integer>();

        while (true) {
            int x = Integer.parseInt(br.readLine());

            if (x == -1)
                break;
            else if (x == 0)
                dq.pollFirst();
            else if (x > 0 && dq.size() < n)
                dq.addLast(x);
        }

        if (dq.isEmpty()) sb.append("empty");
        else {
            for (int k : dq) {
                sb.append(k).append(" ");
            }
        }

        System.out.println(sb);
    }
}
