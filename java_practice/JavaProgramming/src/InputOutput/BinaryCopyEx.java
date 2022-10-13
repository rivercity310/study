package InputOutput;

import java.io.*;
import java.util.Calendar;

public class BinaryCopyEx {
	public static void main(String[] args) {
		long starts = Calendar.getInstance().get(Calendar.SECOND);
		long startms = Calendar.getInstance().get(Calendar.MILLISECOND);
		System.out.println(starts + " " + startms);
		
		String srcPath = "C:/Program Files/Wireshark/Wireshark.exe";
		String destPath = "c:/Temp/ClonedWireshark.exe";
		File src = new File(srcPath);
		File dest = new File(destPath);
		
		try {
			var fi = new FileInputStream(src);
			var in = new BufferedInputStream(fi);
			var out = new BufferedOutputStream(new FileOutputStream(dest));
			byte buf[] = new byte[1024 * (int)Math.pow(10, 2)];  // 100KB
			
			while (true) {
				int n = fi.read(buf);
				out.write(buf, 0, n);
				if (n < buf.length) break;
			}
			out.flush();

			long ends = Calendar.getInstance().get(Calendar.SECOND);
			long endms = Calendar.getInstance().get(Calendar.MILLISECOND);
			System.out.println(ends + " " + endms);
			
			in.close();
			out.close();
			
			int Second = (int)(ends - starts);
			int Milli = (int)(endms - startms);
			System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
			System.out.printf("소요시간: %ds %dms", Second, Milli);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
