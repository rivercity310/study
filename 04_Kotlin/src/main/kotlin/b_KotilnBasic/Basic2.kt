package b_KotilnBasic

/* [ 2. enum & when,  ] */

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