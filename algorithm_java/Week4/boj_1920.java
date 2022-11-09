package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1920 {
    static List<Integer> arr;
    static StringTokenizer st;
    static final StringBuilder sb = new StringBuilder();
    static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    static int bin_sch(int data) {
        int pl = 0;
        int pr = arr.size() - 1;

        do {
            int pc = (pl + pr) / 2;
            if (arr.get(pc) == data)
                return pc;
            else if (arr.get(pc) < data)
                pl = pc + 1;
            else
                pr = pc - 1;

        } while (pl <= pr);

        return -1;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens())
            arr.add(Integer.parseInt(st.nextToken()));

        Collections.sort(arr);

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());

            if (bin_sch(x) == -1)
                sb.append(0).append("\n");
            else
                sb.append(1).append("\n");
        }

        System.out.print(sb);
    }
}
