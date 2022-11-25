import java.util.*;

class Student {
	private int id;
	private String tel;
	
	public Student(int id, String tel) {
		this.id = id;
		this.tel = tel;
	}
	public int getId() {
		return this.id;
	}
	public String getTel() {
		return this.tel;
	}
}

public class HashMapStudentEx {
	public static void main(String[] args) {
		var s = new Scanner(System.in);
		var map = new HashMap<String, Student>();
		map.put("황승수", new Student(1, "010-0000-0000"));
		map.put("김수연", new Student(2, "010-1050-0601"));
		map.put("김주완", new Student(3, "010-2030-0902"));
		
		while (true) {
			System.out.print("검색할 이름: ");
			String name = s.nextLine();
			if (name.equals("exit")) {
				System.out.println("프로그램 종료....");
				s.close();
				return;
			}
			
			if (name.equals("showAllStudents")) {
				var keys = map.keySet(); 	    // Set<String> keys와 동일
				var it = keys.iterator(); 		// Iterator<String> it와 동일
				
				while (it.hasNext()) {
					String studentName = it.next();
					Student st = map.get(studentName);
					System.out.printf("이름: %s, ID: %d, TEL: %s\n", studentName, st.getId(), st.getTel());
				}
				
				continue;
			}
			
			var student = map.get(name);		// Student student와 동일
			if (student == null)
				System.out.println(name + "은/는 없는 사람입니다.");
			else 
				System.out.printf("ID: %d, TEL: %s\n", student.getId(), student.getTel());
		}
	}
}
