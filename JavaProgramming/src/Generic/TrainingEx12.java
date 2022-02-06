package Generic;

import java.util.Scanner;
import java.util.HashMap;

class Operation {
	private int value;
	
	public Operation(int val) {
		this.value = val;
	}
	
	public void add(int value) {
		this.value += value;
	}
	
	public void sub(int value) {
		this.value -= value;
	}
	
	public int getValue() {
		return this.value;
	}
}

public class TrainingEx12 {
	private HashMap<String, Operation> h;
	private StringBuffer sb;
	private Scanner s;
	
	public TrainingEx12() {
		h = new HashMap<>();
		sb = new StringBuffer();
		s = new Scanner(System.in);
	}
	
	private void prt(String variable) {
		var keys = h.keySet();
		var it = keys.iterator();
		while (it.hasNext()) {
			var key = it.next();
			System.out.printf("%s:%d ", key.toUpperCase(), h.get(key).getValue());
		}
		System.out.printf("\n출력할 결과는 %d. 프로그램 실행 끝!\n", h.get(variable).getValue());
	}
	
	private boolean isIntegerString(String obj) {
		for (int i = 0; i < obj.length(); i++) {
			char temp = obj.charAt(i);
			if (!Character.isDigit(temp)) return false;
		}
		
		return true;
	}
	
	private void run() {
		try {
			System.out.println("슈퍼컴퓨터가 작동합니다. 프로그램을 입력해주세요.");
			while (true) {
				System.out.print(">> ");
				String line = s.nextLine();
				if (line.equals("go")) break;
				else sb.append(line + "\n");
			}
			
			String[] lbString = sb.toString().split("\n");
			for (int i = 0; i < lbString.length; i++) {
				String[] nbString = lbString[i].split("\\s");
				String command = nbString[0];
				String variable = nbString[1];
				String strValue = nbString[2];
				int value = isIntegerString(strValue) ? Integer.parseInt(nbString[2]) : h.get(strValue).getValue();
				
				var obj = h.get(variable);
				switch(command) {
				case "mov": 
					if (!h.containsKey(variable)) h.put(variable, new Operation(value));
					break;
				case "add":
					obj.add(value);
					break;
				case "sub":
					obj.sub(value);
					break;
				case "jn0": 
					if (obj.getValue() != 0) i = value - 1;
					break;
				case "prt": 
					System.out.printf("[prt %s %d]\n", variable, value);
					prt(variable);
					break;
				default: throw new Exception("Wrong Command: " + command);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		var app = new TrainingEx12();
		app.run();
	}
}
