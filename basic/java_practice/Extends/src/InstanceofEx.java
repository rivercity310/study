class PersonI {}
class StudentI extends PersonI {}
class Researcher extends PersonI {}
class Professor extends Researcher {}

public class InstanceofEx {
	static void print(PersonI p) {
		if (p instanceof PersonI)
			System.out.print("Person ");
		if (p instanceof StudentI)
			System.out.print("Student ");
		if (p instanceof Researcher)
			System.out.print("Researcher ");
		if (p instanceof Professor)
			System.out.print("Professor ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		PersonI p1 = new PersonI();
		PersonI p2 = new StudentI();	// 업캐스팅
		PersonI p3 = new Researcher();	// 업캐스팅
		PersonI p4 = new Professor();	// 업캐스팅
		
		print(p1);		// Person
		print(p2);		// Person Student
		print(p3);		// Person Researcher
		print(p4);		// Person Researcher Professor
	}
}
