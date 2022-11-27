package chapter3

/* 코틀린에서 컬렉션 만들기 */
// 코틀린은 자체 컬렉션을 제공하지 않고, 기존 자바 컬렉션을 활용한다
// 다만 코틀린에서는 자바 클래스에 없는 보다 많은 메서드를 쓸 수 있다

val set1 = setOf(1, 6, 6, 7)
val set2 = hashSetOf(1, 7, 53)

val list = arrayListOf<Int>(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
val strings = listOf("first", "second", "third")

@JvmOverloads           // 자바에서 코틀린 함수를 호출할때, 디폴트 파라미터를 사용할 수 있게 해주는 어노테이션
fun <T> joinToString(collection: Collection<T>, separator: String = ", ", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun collection1() {
    println(strings.first())        // 첫번쨰 원소, 마지막 원소 가져오기
    println(strings.last())
    println(set1.max())

    println(joinToString(strings, separator = "; ", prefix = "(", postfix = ")"))  // 인자에 파라미터 이름을 넣기
    println(joinToString(strings))      // 디폴트 파라미터 이용
    println(joinToString(strings, postfix = ";", prefix = "#"))   // 이름 붙인 인자를 사용하여 지정하고 싶은 인자만 전달
}
