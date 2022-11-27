package chapter3

/* 컬렉션 처리 : 가변 길이 인자, 중위 함수 호출, 라이브러리 지원 */

// 1. vararg 키워드 : 호출시 인자 개수가 달라질 수 있는 함수 정의
// - fun listOf<T> (vararg values: T): List<T> {...}


// 2. 중위 함수 호출 구문 : 인자가 하나뿐인 메서드를 간편하게 호출
// 함수를 중위 호출에 사용하게 허용하고 싶다면 infix 변경자를 함수 선언 앞에 추가해야 한다.
// to 함수의 간략한 정의는 다음과 같다.
// infix fun Any.to(other: Any) = Pair(this, other)

// - val map = mapOf(1 to "one", 7 to "seven")
// 1.to("one") -> "to" 메서드를 일반적인 방식으로 호출
// 1 to "one" -> "to" 메서드를 중위 호출 방식으로 호출


// 3. 구조 분해 선언 : 복합적인 값을 분해해서 여러 변수에 나눠 담을 수 있음
// val (number, name) = 1 to "one"  -> Pair의 내용으로 두 변수를 즉시 초기화 (구조 분해 선언)

// to는 확장 함수다.
// mapOf 함수의 선언은 다음과 같다.
// fun <K, V> mapOf(vararg values: Pair<K, V>): Map<K, V>



fun collection3() {
    val arr: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    for ((index, element) in arr.withIndex())
        println("$index : $element")

    val m: Map<Int, String> = mapOf(1 to "one", 2 to "two", 3 to "three")
    for (pr in m)
        println("${pr.key} : ${pr.value}")
}
