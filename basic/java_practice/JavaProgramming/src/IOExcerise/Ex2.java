package IOExcerise;

import java.io.*;
import java.text.SimpleDateFormat;

public class Ex2 {
	
	private void run() {
		String path = "C:\\Temp";
		File[] flists = new File(path).listFiles();
		StringBuffer sb = new StringBuffer();
		
		try {
			for (File k : flists) {
				if (!k.toString().contains("phone")) continue;
				else {
					FileWriter fout = new FileWriter(path + "\\PhoneAll.txt");
					FileInputStream fs = new FileInputStream(k);
					InputStreamReader in = new InputStreamReader(fs, "MS949"); 
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
					char[] ch = new char[4096];				
					
					int c;
					sb.append("--------------------\r\n");
					sb.append("인코딩 형식: " + in.getEncoding() + "\r\n");
					sb.append(k.getName() + "\r\n");
					while ((c = in.read(ch)) != -1) sb.append((char)c);
					sb.append(sdf.format(k.lastModified()) + "\r\n");
					sb.append("--------------------\r\n");
					sb.append("\r\n");
					
					fout.write(sb.toString());
					System.out.println(sb.toString());
					
					in.close();
					fs.close();
					fout.close();
				} 
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		var app = new Ex2();
		app.run();
	}
}
