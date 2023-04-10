
class Person {
	private int weight;		// private 접근 지정
	int age;				// 디폴트 접근 지정
	protected int height;	// protected 접근 지정
	public String name;		// public 접근 지정
	
	// private 멤버인 weight에 접근하기 위한 get/set 메서드
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
}

class Student extends Person {
	public void set() {
		age = 30;
		name = "홍길동";
		height = 175;
		// weight = 99;	  접근 불가 오류
		setWeight(99);
	}
}


public class InheritanceEx {
	public static void main(String[] args) {
		Student s = new Student();
		s.set();
	}
}
