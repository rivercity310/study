package chapter1

// [ 1. Kotlin 맛보기 ]

/**
[ 데이터 클래스 ] -> VO(Value Object)
- age 프로퍼티의 값은 디폴트가 null (따로 지정하지 않은 경우)

 @param
*/
data class Person(val name: String, val age: Int? = null)

fun personTest() : String {
    val persons : List<Person> = listOf(Person("영희"), Person("철수", age = 29), Person("승수", 26))  // 이름 붙인 파라미터
    val oldset : Person = persons.maxBy { it.age ?: 0 }     // 람다식과 엘비스 연산

    return "나이가 가장 많은 사람 : $oldset"           // 문자열 템플릿 : toString() 자동 호출
}

