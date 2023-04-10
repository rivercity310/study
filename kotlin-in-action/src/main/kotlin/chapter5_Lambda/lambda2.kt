package chapter5_Lambda

import java.util.*


/* 컬렉션 함수형 API */

private data class Friend(val name: String, val age: Int)

fun lambda2_test1() {
    // filter와 map 함수
    val friends = listOf(Friend("Seungsu", 26), Friend("suyeon", 23), Friend("Seoungjin", 27), Friend("Alice", 29))

    println(friends.filter { it.age % 2 == 1 })
    println(friends.filter { it.age > 25 })

    println(friends.map { it.age * 2 })
    println(friends.map(Friend::age))
    println(friends.map { it.name })            // 이름만 출력
    println(friends.map(Friend::name))          // 멤버참조를 이용하여 이름만 출력
    println(friends.map { it.name < "B" })      // [false, false, false, true] 반환

    // 체이닝: 27살 이상인 사람의 이름만 출력
    println(friends.filter { it.age >= 27 }.map(Friend::name))

    // map 자료구조에도 적용 가능: filterKeys, mapKeys 함수 -> 키를 걸러내거나 변환, filterValues, mapValues -> 값을 걸러내거나 변환
    val numbers = mapOf(0 to "zero", 1 to "one", 2 to "two")
    println(numbers.mapValues { it.value.uppercase(Locale.getDefault()) })
}

fun lambda2_test2() {
    // all, any : 모든 원소가 술어를 만족, 술어를 만족하는 원소가 하나 이상
    // 드모르간 법칙에 의해 어떤 조건 P에 대해 not all(!all)을 수행한 결과는 not P(!P)에 대해 any를 수행한 결과와 같다.
    val numbers = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four")

    println(numbers.all { it.key < 4 })
    println(numbers.any { it.key < 4 })

    // count : 술어를 만족하는 원소의 개수를 구해준다.
    println(numbers.count { it.key < 3 })

    // find or firstOrNull : 조건을 만족하는 첫번쨰 원소 반환, 없으면 null 반환
    println(numbers.keys.find { it == 5 } )
    println(numbers.values.firstOrNull { it == "four" })

    // groupBy : 리스트를 주어진 키에 따라 여러 그룹으로 이뤄진 맵으로 변경 -> ex) 나이별로 분류하면 반환형은 Map<age, List<Friend>>
    val friends = listOf(Friend("Seungsu", 27), Friend("suyeon", 23), Friend("Seoungjin", 27), Friend("Alice", 29))
    println(friends.groupBy { it.age })
    println(numbers.values.groupBy(String::first))

    // flatMap과 flatten : 중첩된 컬렉션 안의 원소 처리
    // flatMap : 주어진 람다를 컬렉션의 모든 객체에 적용하고, 람다를 적용한 결과 얻어지는 여러 리스트를 한 리스트로 병합

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })        // toList 함수를 문자열에 적용하면 문자 리스트를 만든다 -> 이후 병합해서 결과는 -> ['a', 'b', 'c', 'd', 'e', 'f']

    class Book(val title: String, val authors: List<String>)

    val books = listOf(
        Book("Thursday Next", listOf("Jasper Fforde")),
        Book("Mort", listOf("Terry Pratchett")),
        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
    )

    println(books.flatMap { it.authors }.toSet())       // toSet() -> 결과 리스트에서 중복을 없애고 집합으로 만든다.

    // flatten : 변환할 내용 없이 리스트를 평평하게(1차원으로) 펼친다.
    val arr = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
    println(arr.flatten())    // [1, 2, 3, 4, 5, 6, 7, 8, 9]
}