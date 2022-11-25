package Week2.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10828 {
    static int[] stk;
    static int tos = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        stk = new int[n];

        while (n > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = -2;

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;

                case "pop":
                    x = pop();
                    break;

                case "top":
                    x = top();
                    break;

                case "size":
                    x = size();
                    break;

                case "empty":
                    x = empty();
                    break;
            }

            if (x != -2) sb.append(x).append("\n");
            n--;
        }

        System.out.println(sb);
    }

    static void push(int x) {
        stk[tos++] = x;
    }

    static int pop() {
        return (tos == 0) ? -1 : stk[--tos];
    }

    static int top() {
        return (tos == 0) ? -1 : stk[tos - 1];
    }

    static int empty() {
        return (tos == 0) ? 1 : 0;
    }

    static int size() {
        return tos;
    }
}
