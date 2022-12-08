package chapter7_operator_overloading

import java.lang.IndexOutOfBoundsException
import java.time.LocalDate

// [ 컬렉션과 범위에 대해 쓸 수 있는 관례 ]

// 1. 인덱스로 원소에 접근: get과 set
// 인덱스 연산자([])를 사용해 원소를 읽는 연산 -> get 연산자 메서드, 원소를 쓰는 연산 -> set 연산자 메서드로 변환

// get 관례 구현
// - 응용: 2차원 행렬에서 operator fun Matrix.get(row: Int, col: Int)를 정의 --> matrix[row, col]로 메서드 호출
operator fun Point.get(index: Int): Int = when(index) {
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

fun overload2_test1() {
    val p = Point(10, 20)
    println(p[0] + p[1])
}

// set 관례 구현
data class MutablePoint(var x: Int, var y: Int)

// set이 받는 마지막 파라미터(value)는 대입문에서의 우항값, 첫번째 파라미터(index)는 인덱스 연산자([])의 값
operator fun MutablePoint.set(index: Int, value: Int) = when(index) {
    0 -> x = value
    1 -> y = value
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

fun overload2_test2() {
    val p = MutablePoint(10, 20)
    p[1] = 42
    println(p)
}



// 2. in 관례 구현하기: 객체가 컬렉션에 들어있는지 검사 (멤버십 검사)
// - in 연산자와 대응하는 함수는 contains

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}

fun overload2_test3() {
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(20, 30) in rect)      // in 우항의 객체(rect)는 contains 메서드의 수신 객체가, 좌항의 객체는 contains 메서드의 인자로 전달
}



// 3. rangeTo 관례 구현하기: .. 연산자는 rangeTo 함수를 간략하게 표현하는 방법이다
// ex) start..end -> start.rangeTo(end)

// 만약 어떤 클래스가 Comparable 인터페이스를 구현한다면 rangeTo가 이미 들어있다. (Comparable에 대한 확장함수)
// operator fun <T: Comparable<T>> T.rangeTo(that: T): ClosedRange<T> { }

fun overload2_test4() {
    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)    // 오늘부터 시작해 10일짜리 범위를 만든다 ( now.rangeTo(now.plusDays(10)) )
    println(now.plusWeeks(1) in vacation)       // in 연산자를 통해 어떤 원소가 그 범위 안에 포함되는지 검사
}



// 4. for 루프를 위한 iterator 관례
// for 루프에서도 in 연산자를 사용하지만 이 경우 in의 의미가 다르다 ( for (x in list) { .. } )
// list.iterator()를 호출해서 이터레이터를 얻은 다음, 그 이터레이터에 대해 hasNext와 next 호출을 반복하는 식으로 변환

// 코틀린 표준 라이브러리는 String의 상위 클래스인 CharSequence에 대한 iterator 확장 함수를 제공
// - operator fun CharSequence.iterator(): CharIterator { }    --> 이 라이브러리 함수가 문자열을 이터레이션할 수 있게 해준다

// 날짜 범위에 대해 이터레이터 구현하기
operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object: Iterator<LocalDate> {       // 이 객체는 LocalDate 원소에 대한 iterator를 구현한다
        var current = start     // minimum value in the range
        override fun hasNext() = current <= endInclusive        // compareTo 관례를 사용해 날짜 비교
        override fun next() = current.apply { current = plusDays(1) }
    }

fun overload2_test5() {
    val newYear = LocalDate.ofYearDay(2022, 1)
    val daysOff = newYear.minusDays(1)..newYear    // rangeTo 함수(..)는 ClosedRange의 인스턴스를 반환

    // ClosedRange<LocalDate>에 대한 확장 함수 iterator를 정의했기 때문에 LocalDate의 범위 객체를 for 루프에 사용할 수 있다
    for (dayOff in daysOff) { println(dayOff) }     // daysOff에 대응하는 iterator 함수가 있으면 이터레이션 가능
}



// [ 구조 분해 선언과 component 함수 ]
// 내부에서 구조 분해 선언은 componentN 함수를 호출 (data class의 주 생성자에 있는 프로퍼티에 대해 컴파일러가 자동으로 componentN 함수를 생성)
// val (a, b) = p  -->  val a = p.component1() val b = p.component2()

// 일반 클래스에서 구조 분해를 사용하기 위해서는 componentN 함수를 직접 정의
private class Point3(private val x: Int, private val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}

fun overload2_test6() {
    val (x, y) = Point3(10, 20)     // componentN 함수에 의해 구조 분해 할당 가능
    println("x: $x, y: $y")
}


// 구조 분해 선언을 사용해 여러 값 반환하기 : 모든 값이 들어갈 데이터 클래스를 정의하고 반환 타입을 그 데이터 클래스로 변경
private data class NameComponent(val name: String, val extension: String)

private fun splitFilename(fullName: String): NameComponent {
    val result = fullName.split('.', limit=2)
    return NameComponent(result[0], result[1])
}

fun overload2_test7() {
    val (name, ext) = splitFilename("example.txt")      // 구조 분해 선언 구문을 사용해 데이터 클래스를 푼다
    println("name: $name, ext: $ext")

    // 배열이나 컬렉션에도 componentN 함수가 있다.
    val (name2, ext2) = "example2.txt".split('.', limit=2)      // split 함수 List<String> 반환 -> 구조 분해
    println("name2: $name2, ext2: $ext2")
}


// 구조 분해 선언을 통한 맵에 대해 이터레이션
private fun printEntries(map: Map<String, String>) {
    // 객체를 이터레이션하는 관례와 구조 분해 선언이 사용된다
    // 1. 코틀린 표준 라이브러리에 맵에 대한 확장 함수로 iterator가 들어있다. -> 맵을 직접 이터레이션 가능
    // 2. Map.Entry에 대한 확장 함수로 component1과 component2를 제공 -> 구조 분해 가능
    for ((key, value) in map) println("$key -> $value")
}

fun overload2_test8() {
    val map = mapOf("Oracle" to "Java", "Jetbrains" to "Kotlin")
    printEntries(map)
}