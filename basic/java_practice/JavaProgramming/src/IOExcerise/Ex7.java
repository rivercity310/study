package IOExcerise;

import java.io.*;

public class Ex7 {
	private void run() {
		String path = "C:/Temp";
		File f = new File(path);
		int deleteCount = 0;
		
		for (File k : f.listFiles()) {
			if (k.getName().contains(".txt")) {
				System.out.println(k.getName() + " 삭제");
				k.delete();
				deleteCount++;
			}
		}
		
		System.out.printf("총 %d개의 .txt 파일을 삭제하였습니다.", deleteCount);
	}
	
	public static void main(String[] args) {
		var app = new Ex7();
		app.run();
	}
}
