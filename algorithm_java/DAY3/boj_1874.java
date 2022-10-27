package DAY3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_1874 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        try {
            int n = Integer.parseInt(br.readLine());
            Stack<Integer> stk = new Stack<>();

            int bp = 0;
            while (n > 0) {
                int k = Integer.parseInt(br.readLine());

                if (k > bp) {
                    for (int i = bp + 1; i <= k; i++) {
                        stk.push(i);
                        sb.append("+\n");
                    }
                    bp = k;
                }

                if (stk.peek() != k) {
                    System.out.println("NO");
                    return;
                }

                stk.pop();
                sb.append("-\n");

                n--;
            }

            System.out.println(sb);
        }
        finally {
            if (br != null) br.close();
        }
    }
}
