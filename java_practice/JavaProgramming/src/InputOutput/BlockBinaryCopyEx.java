package InputOutput;

import java.io.*;
import java.util.Calendar;

public class BlockBinaryCopyEx {
	public static void main(String[] args) {
		long starts = Calendar.getInstance().get(Calendar.SECOND);
		long startms = Calendar.getInstance().get(Calendar.MILLISECOND);
		System.out.println(starts + " " + startms);
		
		File src = new File("c:/Windows/Web/Wallpaper/ThemeC/img29.jpg");
		File dest = new File("c:/Temp/copyimg4.jpg");
		
		try {
			FileInputStream fi = new FileInputStream(src);
			FileOutputStream fo = new FileOutputStream(dest);
			byte buf[] = new byte[1024*10];   // 10KB 버퍼
			
			while (true) {
				int n = fi.read(buf);   // 버퍼 크기만큼 읽기, n은 실제 읽은 바이트
				fo.write(buf, 0, n);    // buf[0]부터 n바이트 쓰기
				if (n < buf.length) break;
			}
			fo.flush();
			
			long ends = Calendar.getInstance().get(Calendar.SECOND);
			long endms = Calendar.getInstance().get(Calendar.MILLISECOND);
			System.out.println(ends + " " + endms);
			
			fi.close();
			fo.close();
			int Second = (int)(ends - starts);
			int Milli = (int)(endms - startms);
			System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
			System.out.printf("소요시간: %ds %dms", Second, Milli);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
