package IOExcerise;

import java.io.*;
import java.util.Scanner;

public class Ex3 {
	private void run() {
		try {
			String path = "C:\\windows\\system.ini";
			File f = new File(path);
			FileInputStream fin = new FileInputStream(f);
			Scanner s = new Scanner(fin);

			int i = 1;
			while (s.hasNext()) {
				System.out.print((i++) + ": ");
				String line = s.nextLine();
				System.out.println(line);
			}
			
			s.close();
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		var app = new Ex3();
		app.run();
	}
}
