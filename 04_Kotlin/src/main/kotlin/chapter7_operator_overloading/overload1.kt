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
// - ==, != 연산자 -> equals 메서드 호출, 널 검사 ( a == b  --->  a?.equals(b) ?: (b == null) ), a가 null이라면 b도 널인 경우에만 결과가 true
// - 위에서 작성한 Point 클래스는 data class 이므로 컴파일러가 자동으로 equals 메서드를 생성해준다.
// - 직접 구현해본다면 다음과 비슷한 코드가 된다.
private class Point2(val x: Int, val y: Int) {
    // equals 메서드는 Any에 정의된 메서드이므로 override 키워드 필요, Any에 정의된 equals에는 operator가 붙어있다.
    // 하지만 오버라이드하는 메서드 앞에는 operator 변경자를 붙이지 않아도 자동으로 상위 클래스의 operator 지정이 적용된다. (생략 가능)
    // - override operator fun equals(other: Any?): Boolean { .. }
    override operator fun equals(other: Any?): Boolean {
        // 식별자 비교 연산자(===): 자바의 ==와 동일, 두 피연산자가 같은 객체를 가리키는지 비교 (원시 타입이라면 두 값이 같은지)
        if (other === this) return true             // ==로 비교하면 다시 equals를 호출하기 때문에 무한루프에 빠진다
        if (other !is Point2) return false          // 타입 검사
        return other.x == x && other.y == y
    }
}

fun overload1_test5() {
    val p1 = Point2(10, 10)
    val p2 = Point2(10, 10)

    println(p1 == p2)
    println(p1 != p2)       // != 연산자는 equals의 반환값을 반전시켜 돌려준다
}




// 순서 연산자: compareTo
// - 자바의 Comparable 인터페이스에 들어있는 compareTo 메서드는 한 객체와 다른 객체의 크기를 비교해 정수로 나타내준다.
// - 코틀린에서는 Comparable 인터페이스 안에 있는 compareTo 메서드를 호출하는 관례를 제공한다. (<, >, >=, <=  --> compareTo 호출로 컴파일)
// - a >= b --> a.compareTo(b) >= 0 (두 객체를 비교하는 식은 compareTo의 결과를 0과 비교하는 코드로 컴파일된다)
class Person(private val firstName: String, private val lastName: String): Comparable<Person> {
    // Comparable의 compareTo 메서드에도 operator 변경자가 붙어있으므로 하위 클래스에서 생략
    override operator fun compareTo(other: Person): Int {
        // compareValuesBy: 두 객체를 인자로 넘겨진 두 비교 함수를 통해 비교 (모든 함수가 0을 반환하면 0 반환)
        // 각 비교함수는 람다나 프로퍼티/메서드 참조일 수 있다.
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }
}

fun overload1_test6() {
    val p1 = Person("Alice", "Smith")
    val p2 = Person("Bob", "Johnson")
    println(p1 < p2)

    // Comparable 인터페이스를 구현하는 모든 자바 클래스를 비교 가능
    println("abc" < "abd")
}
