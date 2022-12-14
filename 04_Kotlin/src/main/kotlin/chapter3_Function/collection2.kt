package chapter3_Function

// 메서드를 다른 클래스에 추가

// 확장 함수 : 어떤 클래스의 멤버 메서드인 것처럼 호출할 수 있지만 그 클래스 밖에 선언된 함수, 하지만 클래스 내 private, protected 멤버 사용 불가
private fun String.lastChar(): Char = this[this.length - 1]       // 수신 객체 타입 뒤에 .(메서드 이름)

// 확장 함수로 유틸리티 함수 정의
private fun <T> Collection<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

// 문자열 컬렉션에 대해서만 호출할 수 있는 join 함수 정의
fun Collection<String>.join(separator: String = ", ", prefix: String = "", postfix: String = "") =
    joinToString(separator, prefix, postfix)


// 확장 함수는 오버라이드할 수 없다.
open class View {
    open fun click() = println("View Clicked")
}

class Button: View() {
    override fun click() = println("Button Clicked")
}

fun View.showOff() = println("I'm View")
fun Button.showOff() = println("I'm button")



// 확장 프로퍼티
val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }


fun collection2() {
    val arr = listOf(1, 2, 3, 4, 5)
    println(arr.joinToString())

    val arr2 = listOf("Kotlin", "Java", "C++")
    println(arr2.join())

    val view: View = Button()
    view.click()        // 런타임 시점에 객체 타입에 따라 동적으로 메서드 호출
    view.showOff()      // 컴파일 시점에 알려진 변수 타입에 따라 정해진 메서드 호출

    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}