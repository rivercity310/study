package IOExcerise;

import java.io.*;

public class Ex5 {
	private void run() {
		String path = "C:\\Temp\\copyimg4.jpg";
		String dest = "C:\\Temp\\b.jpg";
		File originalImg = new File(path);
		System.out.printf("%s를 b.jpg로 복사합니다.\n", originalImg.getName());
		
		try {
			FileInputStream fin = new FileInputStream(originalImg);
			BufferedInputStream in = new BufferedInputStream(fin);
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
			byte[] buf = new byte[(int)originalImg.length() / 10];
			
			while (true) {
				int n = in.read(buf);
				out.write(buf, 0, n);
				if (n < buf.length) break;
			}
			
			out.close();
			in.close();
			fin.close();

		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		var app = new Ex5();
		app.run();
	}
}
