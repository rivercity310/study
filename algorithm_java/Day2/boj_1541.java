package Day2;

import java.util.Scanner;

public class boj_1541 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        String a[] = s.split("-");
        int ans = 0;

        for (int i = 0; i < a.length; i++) {
            String b[] = a[i].split("\\+");
            if (i == 0)
                for (int j = 0; j < b.length; j++)
                    ans += Integer.parseInt(b[j]);
            else
                for (int j = 0; j < b.length; j++)
                    ans -= Integer.parseInt(b[j]);
        }

        System.out.println(ans);
        scan.close();
    }
}
