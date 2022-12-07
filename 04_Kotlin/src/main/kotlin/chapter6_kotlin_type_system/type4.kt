package chapter6_kotlin_type_system

import java.io.BufferedReader
import java.lang.NumberFormatException

// [ 컬렉션과 배열 ]
// - 코틀린 컬렉션은 자바 라이브러리를 바탕으로 만들어졌고, 확장 함수를 통해 기능을 추가한다.

// 타입 인자로 쓰인 Int? -> null 값을 포함할 수 있음
fun realNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()

    for (line in reader.lineSequence()) {
        val numOrNull = line.toIntOrNull()
        result.add(numOrNull)

        /*
        try {
            val number = line.toInt()
            result.add(number)
        } catch(e: NumberFormatException) {
            result.add(null)
        }
        */
    }

    return result
}


fun addValidNumber(numbers: List<Int?>) {
    var sumOfValidNumbers = 0
    var invalidNumbers = 0

    for (number in numbers) {         // Int? -> null handling
        if (number != null) sumOfValidNumbers += number
        else invalidNumbers++
    }

    println("sum of valid numbers: $sumOfValidNumbers")
    println("invalid numbers: $invalidNumbers")
}


fun addValidNumber2(numbers: List<Int?>) {
    // filterNotNull: nullable collection에서 null 값을 걸러내주는 함수
    val validNumbers = numbers.filterNotNull()       // validNumbers의 타입은 List<Int>가 된다.
    println("sum of valid numbers: ${validNumbers.sum()}")
    println("invalid numbers: ${numbers.size - validNumbers.size}")
}


fun type4_test1() {
    val lst = ArrayList<Int?>()
    lst.add(1)
    lst.add(2)
    lst.add(null)
    lst.add(4)

    addValidNumber(lst)
    addValidNumber2(lst)
}




// 읽기 전용 컬렉션, 변경 가능 컬렉션
// - 코틀린에서는 읽기 전용 인터페이스와 변경 가능 인터페이스를 분리했다. (Collection & MutableCollection)
// - 예를 들어, MutableCollection 인터페이스는 Collection 인터페이스를 확장하면서 원소 추가, 삭제, 클리어 등의 메서드를 더 제공한다.

// 아래와 같이 인자의 타입을 설정하면 target에 읽기 전용 컬렉션을 넘길 수 없다.
// 실제 그 컬렉션이 변경 가능한 컬렉션인지 여부와 관계 없이 선언된 타입이 읽기 전용이면 target에 넘기면 컴파일 오류
fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (item in source)
        target.add(item)
}

fun type4_test2() {
    val source: Collection<Int> = arrayListOf(1, 2, 3, 4)
    val target: MutableCollection<Int> = arrayListOf()

    copyElements(source, target)
    println(target)
}




// 컬렉션 타입       읽기 전용 타입        변경 가능 타입
//  List            listOf            mutableListOf, arrayListOf
//  Set             setOf             mutableSetOf, hashSetOf, linkedSetOf, sortedSetOf
//  Map             mapOf             mutableMapOf, hashMapOf, linkedMapOf, sortedMapOf




// 원시 타입 배열

// - Array 생성자는 배열 크기와 람다를 인자로 받아서 람다를 호출해 각 배열 원소를 초기화
fun type4_test3() {
    val letters = Array<String>(26) { i -> ('a' + i).toString() }       // 코틀린의 Array는 컬렉션이 아닌 배열이다.
    println(letters.joinToString(""))

    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))     // vararg 인자를 넘기기 위해 스프레드 연산자(*) 사용, toTypedArray -> 컬렉션을 배열로 변환
}


// 다른 제네릭 타입에서처럼 배열 타입의 타입 인자로 항상 객체 타입이 된다. (Array<Int> -> 박싱된 정수의 배열(Integer 형)이다)
// 박싱하지 않은 원시 타입 배열이 필요하다면 원시 타입 배열을 표현하는 별도 클래스를 사용한다. (IntArray, ByteArray ...  -> 자바의 Int[], Byte[]로 컴파일 )
// 원시 타입인 원소로 이뤄진 배열에도 컬렉션에서 제공되는 모든 확장 함수를 사용할 수 있다. (단 그러한 함수는 배열이 아닌 리스트를 리턴한다.)


