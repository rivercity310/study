package InputOutput;

import java.io.File;

public class FileEx {
	public static void listDirectory(File dir) {
		System.out.println("-----" + dir.getPath() + "의 서브 리스트입니다. -----");
		File[] subFiles = dir.listFiles();
		
		for (File f : subFiles) {
			long t = f.lastModified();  // 마지막으로 수정된 시간
			System.out.print(f.getName());
			System.out.print("\t파일 크기: " + f.length());
			System.out.printf("\t수정한 시간: %tb %tb %ta %tT", t, t, t, t);
			System.out.print("\t" + t + "\n");
		}
	}
	
	public static void main(String[] args) {
		File f1 = new File("c:\\windows\\system.ini");
		System.out.println(f1.getPath() + ", " + f1.getParent() + ", " + f1.getName());
		
		String res = f1.isFile() ? "파일" : f1.isDirectory() ? "디렉토리" : "?";
		System.out.println(f1.getPath() + "은 " + res + "입니다.");
		
		File f2 = new File("C:\\Temp\\java_sample");
		if (!f2.exists()) f2.mkdir(); // f2 디렉터리가 존재하지 않으면 디렉토리 생성
		
		listDirectory(new File("c:\\Temp"));  // c:\\Temp에 있는 파일 리스트 출력
		f2.renameTo(new File("c:\\Temp\\javasample"));
		listDirectory(new File("c:\\Temp"));
		
	}
}
