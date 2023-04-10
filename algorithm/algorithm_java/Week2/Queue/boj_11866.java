package Week2.Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj_11866 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Queue<Integer> q = new LinkedList<>();

        int n = s.nextInt();
        int k = s.nextInt();

        for (int i = 1; i <= n; i++)
            q.add(i);

        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k - 1; j++)
                q.add(q.remove());

            ans[i] = q.remove();
        }

        StringBuilder sb = new StringBuilder();

        sb.append("<");
        for (int i = 0; i < n; i++) {
            sb.append(ans[i]);
            if (i != n - 1) sb.append(", ");
        }
        sb.append(">");

        System.out.println(sb);
    }
}
