package Thread;

public class MainThread {
	public static void main(String[] args) {
		long id = Thread.currentThread().getId();               // 스레드 ID
		String name = Thread.currentThread().getName();         // 스레드 이름
		int priority = Thread.currentThread().getPriority();    // 스레드 우선순위값
		
		Thread.State s = Thread.currentThread().getState(); 	// 스레드 상탯값
		
		System.out.println("현재 스레드 이름: " + name);
		System.out.println("현재 스레드 ID: " + id);
		System.out.println("현재 스레드 우선순위 값: " + priority);
		System.out.println("현재 스레드 상태: " + s);
	}
}
