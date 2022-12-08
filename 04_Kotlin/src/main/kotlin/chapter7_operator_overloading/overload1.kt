package chapter7_operator_overloading

import java.math.BigDecimal

// 코틀린에서는 [어떤 언어 기능과 미리 정해진 이름의 함수를 연결해주는 기법]을 "관례(Convention)"이라고 부른다.
// - 언어 기능을 타입에 의존하는 자바와 달리 코틀린은 함수 이름을 통한 관례에 의존한다. (plus, minus, div, times, mode(rem) .. )
// - 기존 자바 클래스를 구현하는 인터페이스는 이미 고정돼 있기 때문에 코틀린 쪽에서는 확장 함수를 사용하여 기존 클래스에 새로운 메서드를 추가

// 1. 산술 연산자 오버로딩
data class Point(val x: Int, val y: Int) {
    // operator 변경자를 추가해 plus 함수를 선언하고 나면 + 기호로 두 Point 객체를 더할 수 있다.
    operator fun plus(other: Point): Point {            // 연산자를 오버로딩하는 함수 앞에 operator 키워드를 붙여야 한다
        return Point(x + other.x, y + other.y)
    }
}

// 확장 함수로 정의할 수도 있다.
operator fun Point.minus(other: Point) = Point(x - other.x, y - other.y)
operator fun Point.rem(other: Point) = Point(x % other.x, y % other.y)

// 두 피연산자가 같은 타입일 필요는 없다
// (p * 1.5) 외에 (1.5 * p)라고도 쓸 수 있어야 한다면 따로 정의해주어야 한다. (교환 법칙 지원하지 않음)
operator fun Point.times(scale: Double) = Point((x * scale).toInt(), (y * scale).toInt())
operator fun Double.times(point: Point) = Point((this * point.x).toInt(), (this * point.y).toInt())


// 결과 타입이 피연산자 타입과 달라도 된다
operator fun Char.times(count: Int): String {
    return this.toString().repeat(count)
}

fun overload1_test1() {
    val p1 = Point(10, 20)
    val p2 = Point(20, 30)

    println(p1 + p2)        // p1.plus(p2)와 같이 plus 함수를 호출하도록 컴파일 된다
    println(p2 - p1)
    println(p2 % p1)

    println(p1 * 5.0)
    println(5.0 * p2)

    println('a' * 10)
}



// 비트 연산자: 중위 연산자 표기법을 지원하는 일반 함수를 사용해 비트 연산을 수행
fun overload1_test2() {
    println(0x0F and 0XF0)      // 비트 곱(&)
    println(0X0F or 0XF0)       // 비트 합(|)
    println(0x1 shl 4)          // 왼쪽 시프트(<<)
    println(0xFF shr 5)         // 오른쪽 시프트(>>)      255 127 63 31 15 7
}



// 복합 대입 연산자 오버로딩 (+=, -= ..)
fun overload1_test3() {
    val numbers = ArrayList<Int>()      // 코틀린 표준 라이브러리는 변경 가능한 컬렉션에 대해 복합 대입 연산자 오버로딩을 지원한다.
    numbers += 42                       // operator fun <T> MutableCollection<T>.plusAssign(element: T): Unit { .. }
                                        // - 반환 타입이 Unit이므로 객체에 대한 참조를 다른 참조로 바꾸는 게 아닌 원래 객체의 내부 상태를 변경 (위의 산술 연산자는 새로운 객체를 반환하므로 객체 참조가 바뀐다)
    println(numbers[0])
}


// 단항 연산자 오버로딩: not(!), inc(++), dec(--), unaryPlus(+), unaryMinus(-)
operator fun Point.unaryMinus(): Point {        // 단항 함수는 파라미터가 없다.
    return Point(-x, -y)
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE       // 전위, 후위 둘다 작동

fun overload1_test4() {
    val p = Point(10, 10)
    println(-p)

    var bd = BigDecimal.ZERO
    println(++bd)
}


// 비교 연산자 오버로딩: equals, compareTo -> ==