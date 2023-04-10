class PersonD {
	String name;
	String id;
	
	public PersonD(String name) {
		this.name = name;
	}
}

class StudentD extends PersonD {
	String grade;
	String department;
	
	public StudentD(String name) {
		super(name);
	}
}


public class DowncastingEx {
	public static void main(String[] args) {
		// StudentD 객체가 PersonD 타입으로 업캐스팅
		PersonD p = new StudentD("황승수");
		
		// PersonD 타입의 p를 StudentD 타입으로 변환: 다운캐스팅
		StudentD s = (StudentD)p;	
		
		System.out.println(s.name);
		s.grade = "A";
		s.department = "Com";
	}
}
