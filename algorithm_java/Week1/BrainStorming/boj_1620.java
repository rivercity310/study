package Week1.BrainStorming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;

public class boj_1620 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        ArrayList<String> lst = new ArrayList<String>();
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            lst.add(tmp);
            map.put(tmp, i);
        }

        for (int i = 0; i < m; i++) {
            String tmp = br.readLine();
            boolean flag = true;

            for (int j = 0; j < tmp.length(); j++) {
                if (!Character.isDigit(tmp.charAt(j))) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                int x = Integer.valueOf(tmp);
                sb.append(lst.get(x - 1));
            }
            else
                sb.append(map.get(tmp) + 1);

            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
