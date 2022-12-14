package chapter8_higher_order_function

import java.util.*

// 람다를 인자로 받거나 반환하는 고차 함수를 만드는 방법
// - 고차함수(Higher-Order Function): 다른 함수를 인자로 받거나 함수를 반환하는 함수,
// 코틀린에서는 함수 참조를 사용해 함수를 값으로 표현할 수 있다.

// 1. 함수 타입
// - 함수 타입을 선언할 때는 반환 타입을 반드시 명시해야 하므로 Unit을 빼먹어서는 안된다.
// - 변수 타입을 함수 타입으로 지정하면 람다 식 안에서 파라미터 타입 생략 가능 (컴파일러가 유추)
fun hof1_ex1() {
    val sum: (Int, Int) -> Int = { x, y -> x + y }  // Int 파라미터 2개를 받아 Int 값을 반환하는 함수
    val action: () -> Unit = { println() }          // 아무 인자도 받지 않고 아무 값도 반환하지 않는 함수
    val canReturnNull: (Int, Int) -> Int? = { x, y -> null }
    val funOrNull: ((Int, Int) -> Int)? = null      // 함수 타입 전체가 nullable
}


// 2. 인자로 받은 함수 호출해보기
private fun twoAndThree(oper: (Int, Int) -> Int) {
    val result = oper(2, 3)
    println("Result is $result")
}

fun hof1_ex2() {
    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }
}

// String에 대한 filter 함수 직접 구현해보기
private fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in indices) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }

    return sb.toString()
}

fun hof1_ex3() {
    println("abc123defg123".filter { it in 'a'..'z' })      // abcdefg
}


// 자바에서 코틀린 함수 타입 사용
// - 컴파일된 코드 안에서 함수 타입은 일반 인터페이스로 바뀐다. 즉 함수 타입의 변수는 FunctionN 인터페이스를 구현하는 객체를 저장한다.
// - 코틀린 표준 라이브러리는 함수 인자의 개수에 따라 Function0<R>(인자가 없는 함수), Function1<P1, R>(인자가 하나인 함수) 등의 인터페이스를 제공
// - 각 인터페이스에는 Invoke 메서드 정의가 들어있는데, invoke를 호출하면 함수를 실행할 수 있다.
// ---> 함수 타입 변수는 인자 개수에 따라 적당한 FunctionN 인터페이스를 구현하는 클래스의 인스턴스를 저장하며, 그 클래스의 invoke 메서드 본문에는 람다의 본문이 들어간다.

private fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

// Java 8 이후 -> 람다를 넘기면 자동으로 함수 타입의 값으로 변환
// processTheAnswer(number -> number + 1);   // 43

/* Java 8 이전 -> FunctionN 인터페이스의 invoke 메서드를 구현하는 무명 클래스를 넘긴다
processTheAnswer(
    new Function1<Integer, Integer>() {
        @Override
        public Integer invoke(Integer number) {
            System.out.println(number);
            return number + 1;
        }
    }
)
*/



// 3. 디폴트 값을 지정한 함수 타입 파라미터
private fun <T> Collection<T>.joinToString(
    separator: String = ", ", prefix: String = "", postfix: String = "", transform: (T) -> String = { it.toString() }): String
{
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element))
    }

    result.append(postfix)
    return result.toString()
}

fun hof1_ex4() {
    val letters = listOf("Alpha", "Beta")
    // println(letters.joinToString())     // 디폴트 변환 함수(toString) 사용
    println(letters.joinToString("!") { it.lowercase(Locale.getDefault()) })     // 람다를 인자로 전달
    println(letters.joinToString(separator = "!", postfix="!", transform = { it.uppercase(Locale.getDefault()) }))   // 이름붙인 인자
}



// 4. nullable 함수 타입
/* nullable 함수 타입은 직접 호출할 수 없다
private fun <T> Collection<T>.joinToString(
    separator: String = ", ", prefix: String = "", postfix: String = "", transform: ((T) -> String)? = null): String
{
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        val str = transform?.invoke(element) ?: element.toString()      // 안전한 호출(?.)과 엘비스 연산자를 통해 함수 호출
        result.append(str)                  // 함수 타입은 invoke 메서드를 구현하는 인터페이스이므로 callback?.invoke() 처럼 호출할 수 있다.
    }

    result.append(postfix)
    return result.toString()
}
*/
