import java.util.Scanner;

public class StringBufferEx {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print(">> ");
        String str = s.nextLine();
        StringBuffer sb = new StringBuffer(str);

        while (true) {
            System.out.print("명령: ");
            String input = s.next();

            if (input.equals("그만")) {
                System.out.print("프로그램을 종료합니다.");
                break;
            }

            try {
                boolean isChange = false;
                if (findMark(input)) {
                    String[] strAry = str.split(" ");
                    String[] cg = input.split("!");

                    for (int i = 0; i < strAry.length; i++)
                        if (strAry[i].equals(cg[0])) {
                            int start = sb.indexOf(cg[0]);
                            sb.replace(start, start + cg[0].length(), cg[1]);
                            str = sb.substring(0);
                            isChange = !isChange;
                        }
                    
                    String msg = !isChange ? "잘못된 입력입니다." : str;
                    System.out.println(msg);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("배열의 범위를 벗어났습니다.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("문자열의 범위를 벗어났습니다.");
            }

        }

        s.close();
    }

    private static boolean findMark(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '!')
                return true;
        }

        return false;
    }
}