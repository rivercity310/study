class PersonA {
	String name;
	String id;
	
	public PersonA(String name) {
		this.name = name;
	}
}

class StudentA extends PersonA {
	String grade;
	String department;
	
	public StudentA(String name) {
		super(name);
	}
}

public class UpcastingEx {
	public static void main(String[] args) {
		PersonA p;
		StudentA s = new StudentA("황승수");
		p = s;	// 업캐스팅
		
		System.out.println(p.name);
		// p.grade = "A";		// 오류
		// p.department = "Com"; // 오류
	}
}
