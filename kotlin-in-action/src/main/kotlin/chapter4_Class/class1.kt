package chapter4_Class

import java.io.Serializable

// [ 클래스 계층 정의 ]

// 1. 코틀린 인터페이스
// - 추상 메서드뿐 아니라 구현이 있는 메서드도 정의할 수 있다. 다만 아무런 필드도 들어갈 수 없다.

private interface Clickable {
    fun click()         // 추상 메서드
    fun showOff() = println("I'm clickable")   // 디폴트 구현이 있는 메소드
}

private interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")
    fun showOff() = println("I'm focusable")
}

// 두 인터페이스에 동일한 디폴트 메서드가 있을 때, 두 인스페이스를 상속받은 클래스는 어떤 디폴트 메서드를 실행할지 오버라이드 해야한다.
private class Button: Clickable, Focusable {
    override fun click() = println("I was Clicked")     // 추상 메서드 구현 (override 키워드 필수)
    override fun showOff() {
        //super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun class1_test1() {
    println("[ button1 ]")
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()

    println("[ button2 ]")
    val button2: Clickable = Button()
    button2.click()         // Clickable 타입임에도 Focusable의 showOff를 출력 -> 구현 클래스에서 재정의했기 때문
    button2.showOff()

    println("[ button3 ]")
    val button3: Focusable = Button()
    button3.setFocus(false)
    button3.showOff()
}





// 2. 취약한 기반 클래스 문제 -> 기반 클래스를 변경하는 경우 하위 클래스의 동작이 예기치 않게 바뀔 수 있는 위험
// - 코틀린의 클래스와 메서드는 기본적으로 final(상속 금지)이다. 상속을 허용하려면 앞에 open 변경자를 붙인다
// - 기본적인 상속 가능 상태를 final로 함으로써 스마트 캐스트가 가능해진다.

private open class RichButton: Clickable {  // 이 클래스는 열려있다. 다른 클래스에서 상속 가능
    override fun click() {}     // 오버라이드한 함수는 열려있다. 하위 클래스에서 다시 오버라이드 하지 못하게 하려면 final을 붙인다.
    fun disable() {}            // 이 메서드는 final이다. 하위 클래스에서 오버라이드 불가
    open fun animate() {}       // 이 메서드는 열려있다. 하위 클래스에서 오버라이드 가능
}


// - 추상 클래스는 하위 클래스에서 오버라이드 해야하기 때문에 당연히 추상 멤버는 항상 open

private abstract class Animated {           // 이 클래스는 추상클래스다. 이 클래스의 인스턴스 생성 불가
    abstract fun animate()          // 이 함수는 추상 함수로 구현이 없다. 하위 클래스에서 반드시 오버라이드해야 한다.
    open fun stopAnimating() {}     // 이미 구현된 멤버에 대해서는 오픈할 수도, 닫을 수도 있다.
    fun animateTwice() {}
}

// - 당연하지만 인터페이스 멤버는 final, open, abstract를 사용하지 않는다. 항상 열려있으며 final로 변경할 수도 없다.






// 3. 가시성 변경자: 기본적으로 공개
// - 코틀린에서 디폴트 가시성은 public, 자바의 디폴트인 패키지 전용 가시성은 코틀린에 존재하지 않는다. 코틀린에서 패키지는 오직 네임스페이스를 관리하기 위한 용도이다.
// - public : 모든 곳에서 볼 수 있음, internal : 같은 모듈(함꼐 컴파일되는 코틀린 파일들) 안에서만 볼 수 있음
// - protected : 하위 클래스 안에서만 볼 수 있음, private : 같은 클래스 안에서만 볼 수 있음

private open class TalkativeButton: Focusable {
    private fun yell() = println("hello")
    protected fun whisper() = println("let's talk")
}

// - 클래스를 확장한 함수는 private, protected 멤버에 접근할 수 없음
// fun TalkativeButton.test() = whisper()



// 4. 내부 클래스와 중첩된 클래스
// - 코틀린의 중첩 클래스는 명시적으로 요청하지 않는 한 바깥쪽 클래스 인스턴스에 대한 접근 권한이 없다
// - 직렬화: 자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로 데이터를 변환하는 기술

private interface State: Serializable

private interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

// - 자바에서는 중첩 클래스를 static으로 선언하면 바깥 클래스에 대한 묵시적인 참조가 사라진다.
// - 코틀린 중첩 클래스에 아무런 변경자가 붙지 않으면 자바의 static class와 같다
private class NewButton: View {
    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) {}
    class ButtonState: State {}
}

// - 만약 내부 클래스로 변경해서 바깥쪽 클래스에 대한 참조를 포함하고 싶다면 inner 변경자를 붙인다.
private class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer     // -> 내부 클래스에서 바깥쪽 클래스의 참조에 접근하려면 this@outer라고 써야한다.
    }
}

// 즉 정리하자면 다음과 같다
// - 중첩 클래스(바깥쪽 클래스에 대한 참조 X): 자바에서는 static class, 코틀린에서는 class
// - 내부 클래스(바깥쪽 클래스에 대한 참조 O): 자바에서는 class, 코틀린에서는 inner class





// 5. 봉인된 클래스: 클래스 계층 정의 시 계층 확장 제한

// - 봉인된 클래스(sealed)는 클래스 외부에 자신을 상속한 클래스를 둘 수 없다

private sealed class Expr {                     // 기반 클래스를 sealed로 봉인한다
    class Num(val value: Int): Expr()   // 기반 클래스의 모든 하위 클래스를 중첩 클래스로 나열한다.
    class Sum(val left: Expr, val right: Expr): Expr()
}

// - 더이상 when에서 디폴트 분기(else)를 강제하지 않는다
private fun eval(e: Expr): Int = when(e) {
    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.left) + eval(e.right)
}