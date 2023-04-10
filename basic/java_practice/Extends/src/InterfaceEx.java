interface PhoneInterface {
	// 상수 필드, public static final 생략 가능
	public static final int TIMEOUT = 10000;
	
	// 추상 메소드, public abstract 생략 가능
	public abstract void sendCall();
	public abstract void receiveCall();
	
	// default 메소드, public 생략 가능
	public default void printLogo() {
		System.out.println("My Phone");
	}
}

interface MobilePhoneInterface extends PhoneInterface {
	// 추상 메소드, public abstract 생략 가능
	void sendSMS();
	void receiveSMS();
}

interface MP3Interface {
	void play();
	void stop();
}

// 다중 상속 가능
interface MusicPhoneInterface extends MobilePhoneInterface, MP3Interface {
	void playMP3RingTone();
}

class PDA {
	public int calculate(int x, int y)  {
		return x + y;
	}
}


// SamsungPhone 클래스를 PDA를 상속받고,
// MobilePhoneInterface와 MP3Interface에 선언된 추상 메소드들을 모두 구현한다.
class SamsungPhone extends PDA implements MP3Interface, MobilePhoneInterface {
	// MobilePhoneInterface의 모든 추상 메소드 구현
	@Override
	public void sendCall() {
		System.out.println("띠리리리링");
	}
	
	@Override
	public void receiveCall() {
		System.out.println("전화 왔어요~");
	}
	
	@Override
	public void sendSMS() {
		System.out.println("문자갑니다.");
	}
	
	@Override
	public void receiveSMS() {
		System.out.println("문자 왔어요~");
	}
	
	// MP3Interface의 모든 추상 메소드 구현
	@Override
	public void play() {
		System.out.println("음악을 재생합니다.");
	}
	
	@Override
	public void stop() {
		System.out.println("음악을 중단합니다.");
	}
	
	// 메소드 추가 작성
	public void printTimeout() {
		System.out.println(TIMEOUT);
	}
}


public class InterfaceEx {
	public static void main(String[] args) {
		SamsungPhone phone = new SamsungPhone();
		phone.printLogo();
		phone.sendCall();
		phone.receiveCall();
		phone.sendSMS();
		phone.receiveSMS();
		phone.play();
		phone.stop();
		System.out.printf("3과 5를 더하면 %d\n", phone.calculate(3, 5));
		phone.printTimeout();
	}
}
