package chapter2

import java.lang.IllegalArgumentException

/* [ 2. enum & when, Smart Cast ] */

// 코틀린에서 enum은 class 앞에 있을 때만 특별한 의미를 지니는 소프트 키워드 -> enum 혼자 쓸때는 의미 X, enum이라는 변수명을 만들 수 있음
// 자바에서는 단순히 enum을 사용하여 선언하므로, enum은 키워드

/*
- 값을 열거하는 enum class
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}
*/

// - 프로퍼티와 메서드가 있는 enum class
enum class Color (private val r: Int, private val g: Int, private val b: Int) {
    RED(255, 0, 0), ORANGE(255, 165, 0), YELLOW(255, 255, 0);     // enum class 안에서 메서드를 정의하는 경우 유일하게 이 부분에 세미콜론 필수

    fun rgb() = (r * 256 + g) * 256 + b
}

// 다음과 같이 사용 -> println(Color.RED.rgb())

// when -> switch에 해당, 코틀린에서는 when도 if와 마찬가지로 식이므로 식이 본문인 함수에 when을 바로 사용 가능
fun getMnemonic(color: Color) = when (color) {
        Color.RED -> "Richard"
        Color.ORANGE, Color.YELLOW -> "Of"        // 한 분기 안에 여러 값 사용하기 (콤마로 분리)
        else -> throw Exception("No Color")
    }

fun mix(color1: Color, color2: Color) = when(setOf(color1, color2)) {       // when 식의 인자로 아무 객체나 사용 가능 !!
    setOf(Color.RED, Color.YELLOW) -> "ORANGE"
    setOf(Color.RED, Color.ORANGE) -> "GREEN"
    else -> throw Exception("Dirty Color")
}

// 인자 없는 when 사용 -> mix 함수의 불필요한 Set 가비지 객체가 늘어나는 것 방지
fun mix2(color1: Color, color2: Color) = when {
    (color1 == Color.RED && color2 == Color.YELLOW) || (color1 == Color.YELLOW && color2 == Color.RED) -> "ORANGE"
    else -> throw Exception("Dirty color")
}



// [ Smart Cast ] : 타입 검사와 타입 캐스트를 조합

interface Expr  // 아무 메서드도 선언하지 않고 단지 여러 타입의 식 객체를 아우르는 공통 타입 역할만 수행
class Num(val value: Int): Expr         // value라는 프로퍼티만 존재하는 단순 클래스로 Expr 인터페이스를 구현한다
class Sum(val left: Expr, val right: Expr): Expr       // Expr 타입의 객체라면 어떤 것이나 Sum 연산의 인자 (Num이나 Sum이 인자로 올 수 있다)

/* java style code */
fun evalJavaStyle(e: Expr): Int {
    if (e is Num) {     // is -> java의 instanceof (변수 타입을 검사)
        // val n = e as Num              // 코틀린에서 Num 타입으로 변환은 불필요한 중복 -> 스마트 캐스팅
        return e.value                          // is로 검사하는 부분에서 컴파일러가 e의 타입을 자동으로 Num으로 해석하므로 따라서 명시적 캐스팅 필요 없다.
    }
    else if (e is Sum)
        return evalJavaStyle(e.left) + evalJavaStyle(e.right)
    else
        throw IllegalArgumentException("Unknown Expression")
}

/* 위 코드를 if -> when으로 변경해서 코틀린 스타일로 리팩터링 */
fun eval(e: Expr): Int = when(e) {
    is Num -> e.value
    is Sum -> eval(e.left) + eval(e.right)
    else -> throw IllegalArgumentException("Unknown Expression")
}


/* if와 when 모두 분기에 블록을 사용할 수 있다. 블록의 마지막 문장이 블록 전체의 결과가 된다. */
fun evalWithLogging(e: Expr): Int = when(e) {
    is Num -> {
        println("num: ${e.value}")
        e.value
    }
    is Sum -> {
        val left = evalWithLogging(e.left)
        val right = evalWithLogging(e.right)
        println("sum: $left + $right")
        left + right
    }
    else -> throw IllegalArgumentException("Unknown Expression")
}