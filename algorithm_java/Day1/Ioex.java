package Day1;

import java.io.*;

public class Ioex {
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String test = br.readLine();

        // Output
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.append("test").append(" ").append("output").append("\n");
        bw.flush();
        bw.close();

        // Output2 (가장 빠름)
        StringBuilder sb = new StringBuilder();
        sb.append(test);
        System.out.println(sb);
    }
}
