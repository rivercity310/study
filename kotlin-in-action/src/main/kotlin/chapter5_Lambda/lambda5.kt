package chapter5_Lambda;

import java.io.File
import java.util.*

fun salute() = println("Salute!")

class Lambda_Test {

    private data class Person(val name: String, val age: Int)
    fun lambda5_test1() {

        // 1. 단순 함수나 프로퍼티를 반환하는 역할의 람다는 멤버 참조식으로 대치 가능
        val people: List<Person> = listOf(Person("seungsu", 26), Person("sudang", 23), Person("Bob", 40))
        println(people.maxBy { it.age })
        println(people.minBy { it.name })

        println(people.maxBy(Person::age))
        println(people.minBy(Person::name))

        // 2. 람다 식을 변수에 저장할 수도 있다
        val sum = { x: Int, y: Int -> x + y }
        println(sum(3, 4))

        // 3. 코드의 일부분을 블록으로 둘러싸 실행하는 경우 run을 사용 (run은 인자로 받은 람다를 실행해주는 함수)
        run { println(42) }

        // 4. 리스트의 원소를 toString이 아닌 다른 방식을 통해 문자열로 변환하고 싶은 경우 joinToString 마지막 원소로 람다 전달
        println(people.joinToString(separator = ", ", transform = { p: Person -> p.name }))
        println(people.joinToString { it.name })    // 람다의 파라미터가 하나뿐일때 it 사용 가능
    }

    fun lambda5_test2() {

        // 1. 람다 안에서 바깥 함수의 로컬 변수 변경하기
        fun printProblemCounts(responses: Collection<String>) {
            var clientError = 0
            var serverError = 0

            responses.forEach {
                if (it.startsWith("4")) clientError++           // 람다가 포획(capture)한 변수
                else if (it.startsWith("5")) serverError++
            }

            println("($clientError, $serverError)")
        }

        val response = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
        printProblemCounts(response)

        // 2. 멤버 참조 (함수를 값으로) : 프로퍼티나 메서드를 단 하나만 호출하는 함수 값을 만들어줌
        run(::salute)       // 최상위에 선언된 함수나 프로퍼티 참조 가능

        // 2-1. 작업 위임시에도 위임 함수에 대한 참조를 제공
        fun sendEmail(person: Person, message: String) = println("${person.name} << $message")

        val action = { person: Person, message: String -> sendEmail(person, message) }
        val action2 = ::sendEmail
        action(Person("seungsu", 25), "hi")
        action2(Person("sudang", 23), "hello")

        // 2-2. 생성자 참조 -> 클래스 생성 작업을 연기하거나 저장해둠
        val createPerson = ::Person
        val p = createPerson("Alice", 29)
        println(p)
    }


    fun lambda5_test3() {
        // 1. filter: 입력 컬렉션 원소 중에서 주어진 술어를 만족하는 원소만으로 이뤄진 새로운 컬렉션 리턴
        // true/false를 반환하는 함수를 술어(predicate)라 한다.
        val people = listOf(Person("Alice", 30), Person("Bob", 31), Person("Carol", 31))
        println(people.filter { it.age > 30 })

        // 2. map: 주어진 람다를 각 원소에 적용한 결과를 모아 새 컬렉션을 만든다
        val list = listOf(1, 2, 3, 4)
        println(list.map { it * 2 })
        println(people.map { it.name })
        println(people.filter { it.age > 30 }.map { it.name })
        println(people.filter { it.age <= 30 }.map(Person::name))

        // 3. map에 람다 적용
        val numbers = mapOf(0 to "zero", 1 to "one", 2 to "two")
        println(numbers.mapValues { it.value.uppercase(Locale.getDefault()) })
        println(numbers.filterKeys { it >= 1 }.mapValues { it.value.uppercase(Locale.getDefault()) })
        println(numbers.filterKeys { it >= 1 }.map { it.key * 2 })

        // 4. all, any, count, find : 컬렉션에 술어 적용
        // filter 결과의 크기를 가져올 때 size를 사용하지 말고 count를 써야한다 (size는 중간 컬렉션을 또 만들기 때문에 비효율적)
        println(people.all { it.age > 30 })
        println(people.any { it.age > 30 })
        println(people.count { it.age > 30 })
        println(people.find { it.age > 30 })            // find: 술어를 만족하는 첫번째 원소를 반환, 없으면 null
        println(people.firstOrNull { it.age > 30 })     // find와 같지만 null이 나온다는 사실을 더 명확히 하기 위해 firstOrNull() 사용

        // 5. groupBy: 리스트를 여러 그룹으로 이뤄진 맵으로 변경 (그래프(인접 리스트) 입력받을 때 유용)
        println(people.groupBy { it.age })
        val lst = listOf("a", "ab", "b")
        println(lst.groupBy(String::first))

        // 6. flatMap, flatten: 중첩된 컬렉션 안의 원소 처리
        // - 리스트의 리스트가 있는데 모든 중첩된 리스트의 원소를 한 리스트로 모아야 한다 -> flatMap
        // - 특별히 변환할 내용 없이 리스트의 리스트를 평평하게 펼치기만 하면 된다 -> flatten
        val strings = listOf("abc", "def")
        println(strings.flatMap { it.toList() })        // flatMap: 람다를 모든 원소에 적용(map) 후 여러 리스트를 한 리스트로 모음(flat)
    }


    fun lambda5_test4() {

        // 1. 지연 계산 컬렉션 연산 (시퀀스(자바 스트림)를 사용하여 중간 임시 컬렉션 없이 컬렉션 연산을 연쇄하기)
        listOf(1, 2, 3, 4).asSequence()
            .map { print("map($it) "); it * it }
            .filter { print("filter($it) "); it % 2 == 0 }
            .toList()           // 이때 비로소 연산이 실행됨 (최종 연산이 호출될 때 -> 결과를 얻을 필요가 있을 때)

        // 2. 다음과 같이 시퀀스를 직접 만들 수 있다
        val num = generateSequence(0) { it + 1 }
            .takeWhile { it <= 100 }
            .sum()      // 연산이 수행되는 시점
        println(num)

        // - 조건을 만족하는 디렉터리를 찾은 뒤에는 더이상 상위 디렉터리를 뒤지지 않음
        fun File.isInsideHiddenDirectory() = generateSequence(this) { it.parentFile }.any { it.isHidden }

        val file = File("./workspace/study/04_Kotlin")
        println(file.isInsideHiddenDirectory())

        // 3. 수신 객체 지정 람다: with, apply  ( 수신 객체를 명시하지 않고 람다 본문 안에서 다른 객체의 메서드를 호출할 수 있게 함 )
        // - with: 첫번째 인자로 받은 객체를 두 번째 인자로 받은 람다의 수신 객체로 만든다
        fun alphabet() = with(StringBuilder()) {
            for (letter in 'A'..'Z') append(letter)
            append("\nNow I Know the Alphabet")
            this.toString()
        }

        // - apply: 항상 자신에게 전달된 객체(수신 객체)를 반환  ( 객체의 인스턴스를 만들면서 즉시 프로퍼티 일부를 초기화해야 하는 경우 유용 )
        fun alphabet2() = StringBuilder().apply {
            for (letter in 'A'..'Z') append(letter)
            append("\nNow I Know the Alphabet")
        }.toString()        // 자기 자신(StringBuilder) 반환

        // - buildString: 인자는 수신 객체 지정 람다, 수신 객체는 항상 StringBuilder가 된다. -> StringBuilder 객체를 만드는 일과 toString을 호출해주는 일을 알아서 해줌
        fun alphabet3() = buildString {
            for (letter in 'A'..'Z') append(letter)
            append("\nNow I Know the Alphabet")
        }
    }

}