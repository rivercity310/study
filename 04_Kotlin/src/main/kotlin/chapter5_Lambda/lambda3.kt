package chapter5_Lambda

import java.io.File

/* 지연 계산(lazy) 컬렉션 연산 */
// - 시퀀스(sequence)를 사용하면 중간 임시 컬렉션을 거치지 않고 컬렉션 연산을 연쇄할 수 있다.   -> 자바의 스트림과 같은 개념
// ex) 다음과 같이 사용하면 연쇄 호출이 map과 filter 각각의 결과를 담은 리스트를 2개 만든다.    ->    비효율적
// people.map(Person::name).filter { it.startsWith("A") }
// 따라서 각 연산이 컬렉션을 직접 사용하는 대신 시퀀스를 사용하게 만들어야 한다.

// 코틀린 지연 계산 시퀀스는 Sequence 인터페이스에서 시작한다.
// 시퀀스의 원소는 필요할 때 비로소 계산되고, 중간 처리 결과를 저장하지 않고 연산을 연쇄적으로 적용해서 효율적인 계산을 수행한다.
// 크기가 큰 컬렉션에 대해 연산을 연쇄하는 경우에는 시퀀스를 사용하는 것을 규칙으로 삼자.
// 하지만 중간 컬렉션을 생성함에도 불구하고 즉시 계산 컬렉션에 대한 연산이 더 효율적인 경우도 있다.

private data class People(val name: String, val age: Int)

fun lambda3_test1() {
    val people = listOf(People("Bob", 31), People("seungsu", 26), People("suyeoun", 23), People("sungjin", 27))

    val rst = people.asSequence()               // 원본 컬렉션을 시퀀스로 변환
        .map(People::name)                      // 시퀀스도 컬렉션과 똑같은 API를 제공
        .filter { it.startsWith("s") }
        .toList()                               // 결과 시퀀스를 다시 리스트로 변환

    println(rst)

    // 즉시 계산은 전체 컬렉션에 연산을 적용하지만, 지연 계산은 원소를 한번에 하나씩 처리한다.
    // 다음 두 예제는 동일한 4를 출력하지만 시퀀스를 통한 지연 계산 방식과 즉시 계산 방식의 차이가 존재한다.
    // 즉시 계산 방식은 모든 원소에 대해 map 연산을 적용한 뒤에 find의 술어를 만족하는 원소를 찾는 반면, 지연 계산 방식은 원소 하나마다 처리된다. 즉 연산 순서가 다르다.
    println(listOf(1, 2, 3, 4).asSequence().map { it * it }.find { it > 3 })
    println(listOf(1, 2, 3, 4).map { it * it }.find { it > 3 })

    // 컬렉션에 대해 수행하는 연산의 순서도 성능에 영향을 끼친다.
    // filter를 map보다 먼저 적용하자. (map은 모든 원소에 대해 적용되는 함수이므로 filter로 먼저 원소 개수를 줄이는 것이 효율적이다.)
    println(people.asSequence().filter { it.name.length < 4 }.map(People::name).toList())
}

fun lambda3_test2() {
    // 시퀀스 만들기 : generateSequence 함수
    val naturalNumbers = generateSequence(1) { it + 1 }     // 첫번째 원소를 지정하고, 시컨스의 한 원소로부터 다음 원소를 계산하는 방법을 제공하여 시퀀스를 만든다.
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())         // 모든 지연 계산은 sum의 결과를 계산할 때 수행된다.

    val expoNumbers = generateSequence(2) { it * 2 }
    val expoNumbersTo100 = expoNumbers.takeWhile { it <= 100000 }
    println(expoNumbersTo100.toList())  // 모든 지연 계산은 toList의 결과를 얻을 때 수행된다

    // 시퀀스를 사용해서 어떤 파일의 상위 디렉터리를 뒤지면서 숨김 속성을 가진 디렉터리가 있는지 검사하는 함수
    // 시퀀스를 사용하면 조건을 만족하는 디렉터리를 찾은 뒤에는 더이상 상위 디렉터리를 뒤지지 않는다.
    fun File.isInsideHiddenDirectory() =
        generateSequence(this) { it.parentFile }.find { it.isHidden }

    val file = File("/home/seungsu/workspace/study/.git/hooks")         // 폴더나 파일 앞에 . -> 숨김처리
    println(file.isInsideHiddenDirectory())
}