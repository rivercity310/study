package chapter6_kotlin_type_system

import java.lang.IllegalStateException


// [ 코틀린의 원시 타입 ]
// - 자바에서는 참조 타입이 필요한 경우 특별한 래퍼 타입(Integer)으로 원시 타입 값을 감싸서 사용한다. (Collection<Integer>())
// - 코틀린은 원시 타입과 래퍼 타입을 구분하지 않는다. 실행 시점에 숫자 타입을 가장 효율적인 방식으로 표현한다.
// ==> 대부분의 경우 Int, 컬렉션의 타입 파라미터인 경우에만 Integer 객체 (JVM이 제네릭 클래스의 타입 인자로 원시 타입을 허용하지 않기 때문)

// - null 참는조 자바의 참조 타입의 변수에만 대입할 수 있기 때문에 코틀린의 Int?, Boolean?과 같은 nullable type은 자바의 래퍼 타입으로 컴파일된다.


// 더 나아가 코틀린에서는 원시 타입의 값에 대해 메서드를 호출할 수 있다.
// - ex) coerceIn -> 값을 특정 범위로 제한하는 함수

fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("We're ${percent}% Done!")
}

fun type3_test1() {
    showProgress(145)       // 100으로 제한
    showProgress(-1)        // 0으로 제한
}



// String to Int
fun stoi(s: String) = s.toInt()

fun type3_test2() {
    val test = stoi("42") - stoi("32")
    println(test)
}



// Any, Unit, Nothing 타입

// 1. Any, Any?: 최상위 타입
// - Any 타입은 java.lang.Object와 대응한다. (실제로 Object로 컴파일 된다)
// - 코틀린에서는 Any가 Int 등의 원시 타입을 포함한 모든 타입의 조상 타입이다.
// - 모든 코틀린에 정의된 toString(), equals(), hashCode()는 Any에 정의된 메서드를 상속한 것이다.



// 2. Unit 타입: void
// - void와 달리 Unit을 타입 인자로 쓸 수 있다. (Unit 타입에 속한 값은 단 하나 Unit이다, Unit 타입의 함수는 Unit 값을 묵시적으로 반환한다.)
// - 위 특성은 제네릭 파라미터(T)를 반환하는 함수를 오버라이드하면서 반환 타입으로 Unit을 쓸 때 유용하다.
interface Processor<T> {
    fun process(): T        // 인터페이스의 시그니처는 process 함수가 어떤 값을 반환하라고 요구한다
}

class NoResultProcessor: Processor<Unit> {
    override fun process() {    // Unit을 반환하지만 타입을 지정할 필요가 없다 (묵시적)
        // 여기서 return을 명시할 필요가 없다.
    }
}



// 3. Nothing 타입: 이 함수는 결코 정상적으로 끝나지 않는다. (테스트 라이브러리의 fail, 무한루프 상황, 항상 예외를 던지는 함수 등)
fun fail(message: String): Nothing = throw IllegalStateException(message)
