package IOExcerise;

import java.util.Scanner;
import java.io.*;

public class Ex11 {
	private Scanner s;
	private String p;
	private File f;
	
	public Ex11() {
		p = "C:/";
		s = new Scanner(System.in);
		f = new File(p);
	}
	
	private void pathHub(String query) {
		String[] queryAry = query.split("\\s+");
		
		switch (queryAry[0]) {
		case "mkdir":
			if (queryAry.length == 2) {
				makeDir(queryAry[1]);
				printLists();
			}
			else System.out.println("잘못된 명령입니다.");
			break;
		case "rename":
			if (queryAry.length == 3) {
				rename(queryAry[1], queryAry[2]);
				printLists();
			}
			else System.out.println("잘못된 명령입니다.");
			break;
		case "remove":
			if (queryAry.length == 2) {
				remove(queryAry[1]);
				printLists();
			}
			else System.out.println("잘못된 명령입니다.");
			break;
		default:
			if (queryAry.length == 1) {
				defaultAction(new File(p + query), queryAry);
			}
			else System.out.println("잘못된 명령입니다.");
			break;
		}
	}
	
	private void defaultAction(File asd, String[] queryAry) {
		if (asd.isDirectory()) {
			boolean check = false;
			for (File k : f.listFiles()) {
				if (k.getName().equals(queryAry[0])) {
					check = true;
					p = getNewPath(queryAry[0]);
					printLists();
				}
			}
			
			if (queryAry[0].equals("..")) {
				if (!p.equals("C:/")) {
					p = getNewPath("..");
					printLists();
				} else {
					System.out.println("더이상 이동할 수 없습니다.");
				}
				
				check = true;
			}
			
			if (!check) System.out.println("존재하지 않는 폴더이거나 잘못된 명령입니다.");
		}
		
		// 파일인경우 그 파일의 내용을 출력한다.
		else {
			try {
				Scanner sf = new Scanner(new FileInputStream(asd));
				System.out.println("----------------------------");
				System.out.println("문서를 출력합니다....");
				while (sf.hasNext()) {
					String line = sf.nextLine();
					System.out.println(line);
				}
				System.out.println("----------------------------");
				sf.close();
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		}
	}
	
	private File findFile(String name) {
		for (File k : f.listFiles())
			if (k.getName().equals(name))
				return k;
		
		return null;
	}
	
	private void remove(String forderName) {
		File k = findFile(forderName);
		if (k != null) {
			k.delete();
			System.out.println(forderName + "을 삭제하였습니다.");
		}
		else System.out.println("존재하지 않는 폴더 혹은 파일입니다.");
	}
	
	private void rename(String orgName, String rename) {
		File k = findFile(orgName);
		if (k != null) {
			k.renameTo(new File(p + rename));
			System.out.println(orgName + "을 " + rename + "으로 변경하였습니다.");
		}
		else System.out.println("존재하지 않는 폴더 혹은 파일입니다.");
	}
	
	private void makeDir(String dirName) {
		File newF = new File(p + dirName);
		if (!newF.exists()) newF.mkdir();
		System.out.println(dirName + " 디렉터리를 생성하였습니다.");
	}
	
	private String getNewPath(String move) {		
		if (move.equals("..")) {
			if (!p.equals("C:/")) {
				String[] strAry = p.split("/");
				int begin = p.lastIndexOf(strAry[strAry.length - 1]);
				String res = p.substring(0, begin);
				f = new File(res);
				return res;
			}
			
			// move(사용자 입력값)이 ..이고 p(경로)가 C:/이면 그대로 p를 리턴한다. (더이상 이동할 수 없음) 
			return p;
		}

		String res = p.concat(move + "/");
		f = new File(res);
		return res;
	}
	
	private void printLists() {
		System.out.println("-------------------------------------------------");
		System.out.printf("[%s]\n", p);
			
		for (File k : f.listFiles()) {
			String h = k.isDirectory() ? "dir" : "file";
			System.out.printf("%s\t%d바이트\t%s\n", h, k.length(), k.getName());
		}
		System.out.println("-------------------------------------------------");
	}
	
	private void run() {
		System.out.println("*** 파일 탐색기입니다. ***");
		
		printLists();
		while (true) {
			System.out.print("이동: ");
			String query = s.nextLine();
			
			try {
				// C:\\Temp에 저장됨
				if (query.equals("!maketxt")) {
					var makeTxt = new Ex1();
					makeTxt.run();
					
					s.next();
				}
				else if (query.equals("!break")) {
					System.out.print("프로그램을 종료합니다....");
					return;
				} else {
					pathHub(query);
				}
			} catch (Exception e) {
				System.out.println(e);
				continue;
			}
		} 
	}
	
	public static void main(String[] args) {
		var app = new Ex11();
		app.run();
	}
}
