package Day2;

/*
괄호 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T > 0) {
            var stk = new Stack<Character>();
            String s = br.readLine();

            boolean flag = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') stk.push(c);
                else {
                    if (!stk.empty()) stk.pop();
                    else {
                        flag = false;
                        break;
                    }
                }
            }

            if (stk.empty() && flag) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");

            T--;
        }

        System.out.println(sb);
        br.close();
    }
}
