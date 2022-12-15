package chapter4_Class

import java.io.Serializable
import javax.management.Attribute
import javax.naming.Context
import javax.naming.ContextNotEmptyException
import javax.swing.text.AttributeSet

/*
- java 인터페이스를 kotlin 클래스가 상속 -> java 인터페이스의 default 메서드 자연스럽게 이용 가능
- kotlin 인터페이스를 java 클래스가 상속 -> kotlin 인터페이스의 default 메서드를 java 클래스에서 오버라이드 해주어야 함
     ==> default 메서드 적용 안됨,
*/

private class Test1 {
    private interface Clickable {
        fun click()
        fun showOff() = println("I'm Clickable")
    }

    private interface Focusable {
        fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")
        fun showOff() = println("I'm Focusable")
    }

    private class Button: Clickable, Focusable {
        override fun click() = println("I was Clicked")
        override fun showOff() {
            super<Clickable>.showOff()
            super<Focusable>.showOff()
        }
    }

    fun test() {
        val btn = Button()
        btn.showOff()
        btn.click()
        btn.setFocus(true)
    }
}

private class Test2: Clickable_java, Focusable_java {
    override fun click() = println("clicked")
    override fun showOff() {
        super<Clickable_java>.showOff()
        super<Focusable_java>.showOff()
    }
}

fun class5_ex1() {
    Test1().test()

    val t2 = Test2()
    t2.showOff()
    t2.click()
    t2.setFocus(false)

    val t3 = Btn()
    t3.showOff()
    t3.drag(true)
    t3.sayHi()
}


private class Test3 {
    // 가시성 변경자(접근 변경자): public(default), private, protected, internal(모듈 내)
    //      --> 바이트코드상에서 Kotlin의 private class는 패키지 전용으로, internal은 public으로 컴파일된다.
    //                  ---> 따라서 internal 클래스나 최상위 선언에 모듈 외부의 Java 코드 접근 가능, protected 멤버를 같은 패키지에 속한 Java 코드에서 접근 가능
    //      --> Java default 가시성인 패키지 전용 가시성은 존재하지 않는다. 또한 최상위 선언(함수, 클래스, 프로퍼티)에 대해 private 허용(파일 내부), protected 적용 불가.
    //      --> Java의 protected는 같은 패키지 안에서 접근 가능했지만, Kotlin에서는 해당 클래스나 상속한 클래스 안에서만 이용 가능
    // 상속 제어 변경자: abstract, open, final(default), override
    abstract class Animated {
        abstract fun animate()          // 반드시 오버라이딩 해야함
        open fun stopAnimation() { }    // 오버라이딩 가능
        fun animateTwice() { }          // 오버라이딩 불가
    }
}



private class Test4 {
    interface State : Serializable
    interface View {
        fun getCurrentState(): State
        fun restoreState(state: State) {}
    }

    class Button : View {
        override fun getCurrentState(): State = ButtonState()
        override fun restoreState(state: State) { }

        class ButtonState: State { }        // Java의 static 클래스와 같다 (외부 참조 X)
    }

    // 만약 외부 참조를 가능하게 하려면 inner 변경자를 붙인다.
    class Outer {
        val a = 10
        inner class Inner {
            fun getOuterReference(): Outer {
                println(a)
                println(this@Outer.a)
                return this@Outer
            }
        }

        class Static {
            // fun tmp() = println(a)   --> Error
        }
    }
}



private class Test5 {
    // 봉인된 클래스: 클래스 외부에 자신을 상속한 클래스를 둘 수 없다.
    sealed class Expr {
        class Num(val value: Int): Expr()
        class Sum(val left: Expr, val right: Expr): Expr()
    }

    // when 식에서 디폴드 분기 (else) 제외 가능
    fun eval(e: Expr): Int =
        when(e) {
            is Expr.Num -> e.value
            is Expr.Sum -> eval(e.left) + eval(e.right)
        }
}



// 생성자와 프로퍼티 선언
internal class Test6 {
    class User2 constructor(nickname: String) {
        val nickname: String
        init { this.nickname = nickname }
    }

    class User3(nickname: String, address: String) {     // 프로퍼티를 초기화하는 식이나 초기화 블록 안에서만 주 생성자의 파라미터를 참조할 수 있다.
        val nickname = nickname
        //  fun foo() { println(address) }      Error -> 주 생성자의 파라미터 참조 불가
    }

    open class User(val nickname: String, val isSubscriber: Boolean = true)      // 간략한 문법 & 디폴트 파라미터 가능

    class TwitterUser(nickname: String): User(nickname)     // 상속

    class Secretive private constructor() { }    // 클래스 외부에서 인스턴스화 막기, 동반 객체 안에서 비공개 생성자 호출


    // 부 생성자: Java 상호운영성 때문에 부 생성자가 주로 필요하다
    open class View {
        constructor(ctx: Context) { }
        constructor(ctx: Context, str: String) { }
    }

    class MyBtn: View {
        constructor(ctx: Context): this(ctx, "MY_STYLE")        // 같은 클래스의 다른 생성자에게 생성 위임
        constructor(ctx: Context, str: String): super(ctx, str)     // 부모 클래스의 생성자에게 생성 위임
    }


    // 인터페이스에 선언된 프로퍼티 구현: 코틀린에서는 인터페이스에 추상 프로퍼티 선언을 넣을 수 있다
    interface Person {
        val nickname: String        // 추상 프로퍼티: Person 인터페이스를 구현하는 클래스는 nickname의 값을 얻을 수 있는 방법을 제공해야 한다.
    }

    class privatePerson(override val nickname: String): Person      // 방법1. 주 생성자
    class SubscribingPerson(val email: String): Person {            // 방법2. 커스텀 게터
        override val nickname: String
            get() = email.substringBefore("@")
    }
    class FacebookPerson(val accountId: Int): Person {              // 방법3. 프로퍼티 초기화식
        override val nickname = getFacebookName(accountId)          // 컴파일러가 디폴트 getter/setter 생성
    }

    // setter/getter에서 뒷받침하는 필드에 접근 (방법2 + 방법3)
    class MyUser(val name: String) {
        var address: String = "unspecified"
            set(value: String) {
                println("Address was changed for $name: $field -> $value".trimIndent())     // 뒷받침하는 필드 값 읽기
                field = value       // 뒷받침하는 필드 값 변경
            }
    }

    // 접근자의 가시성도 변경할 수 있다.
    class LengthCounter {
        var counter: Int = 0
            private set         // 이 클래스 바깥에서 이 프로퍼티의 값을 변경할 수 없다 (접근자 가시성은 기본적으로 프로퍼티의 가시성과 같다)

        fun addWord(word: String) { counter += word.length }
    }

    fun test() {
        val user = MyUser("Alice")
        user.address = "Nowon-gu"

        val cnt = LengthCounter()
        // cnt.counter += 3         Error (private set)
        println(cnt.counter)     // Ok (public get)

        cnt.addWord("hello")
        println(cnt.counter)
    }
}
