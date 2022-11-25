package Generic;

import java.util.Scanner;
import java.util.HashMap;

class Location {
	private int longitude, latitude;
	
	public Location(int longi, int latit) {
		this.longitude = longi;
		this.latitude = latit;
	}
	
	public int getLongi() {
		return this.longitude;
	}
	
	public int getLatit() {
		return this.latitude;
	}
}

public class TrainingEx6 {
	
	private void run() {
		var s = new Scanner(System.in);
		var h = new HashMap<String, Location>();
		
		System.out.println("도시, 경도, 위도를 입력하세요.");
		for (int i = 0; i < 4; i++) {
			System.out.print(" >> ");
			String city = s.next();
			int longitude = s.nextInt();
			int latitude = s.nextInt();
			var Loc = new Location(longitude, latitude);
			h.put(city, Loc);
		}
		System.out.println();
		
		var keys = h.keySet();
		var it = keys.iterator();
		while(it.hasNext()) {
			String city = it.next();
			Location loc = h.get(city);
			
			System.out.printf("%s  %d  %d\n", city, loc.getLongi(), loc.getLatit());
		}
		System.out.println();
		
		while (true) {
			System.out.print("도시 이름 >> ");
			String input = s.next();
			
			if (input.equals("그만")) break;
			
			if (h.get(input) == null) System.out.printf("%s는 없습니다.\n", input);
			else System.out.printf("%s  %d  %d\n", input, h.get(input).getLongi(), h.get(input).getLatit());
		}
		
		s.close();
	}
	
	public static void main(String[] args) {
		var app = new TrainingEx6();
		app.run();
	}
}
