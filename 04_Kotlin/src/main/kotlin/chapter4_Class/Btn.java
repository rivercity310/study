package chapter4_Class;

// java 파일에서 kotlin 인터페이스를 상속받으면 디폴트 메서드를 이용할 수 없다. (명시 해주어야 함)
// final 키워드: 클래스 상속 금지
final public class Btn implements Draggable {
    @Override
    public void sayHi() {
        System.out.println("hi");
    }

    @Override
    public void drag(boolean b) {
        System.out.println(b);
    }

    @Override
    public void showOff() {
        System.out.println("showOff");
    }

    final public void DontInheritted() {}       // 메서드 오버라이딩 금지 ( final 클래스 안에서는 안해줘도 됨 )
}