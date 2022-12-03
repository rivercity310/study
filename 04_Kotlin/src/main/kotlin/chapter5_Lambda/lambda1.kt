package chapter5_Lambda

import chapter1.Person

/* 람다와 컬렉션 */
// - 코틀린에서 함수 호출시 맨 뒤에 있는 인자가 람다 식이라면 그 람다를 괄호 밖으로 뺴낼 수 있다.
// - 만약 람다 식이 함수의 유일한 인자라면 괄호를 생략할 수 있다.


fun lambda_test1() {
    data class Employee(val name: String, val age: Int)

    val employee = listOf(Employee("Alice", 29), Employee("Seungsu", 26))
    println(employee.maxBy { it.age })      // 람다 사용
    println(employee.minBy(Employee::age))  // 멤버 참조 사용

    // 람다식을 변수에 저장할 수도 있다
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 5))

    // 바로 호출할 수도 있다 (잘 사용하진 않음)
    println({ x: Int, y: Int -> x % y }(10, 3))
    run { println("Direct") }       // run 함수는 인자로 받은 람다를 실행해주는 라이브러리 함수

    // 리스트의 원소를 toString()이 아닌 다른 방식을 통해 문자열로 반환하고 싶은 경우 joinToString() 사용
    val names = employee.joinToString(" ") { e -> e.name }
    println(names)

    // 람다의 파라미터가 한개뿐이라면 디폴트 이름인 it로 바꾸면 더 간단해진다
    val ages = employee.joinToString(" ") { it.age.toString() }
    println(ages)
}

/* forEach는 컬렉션의 모든 원소에 대해 람다를 호출해준다. */
// - 람다 안에서 바깥의 변수를 변경할 수 있다.
private fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0

    responses.forEach {
        if (it.startsWith("4")) clientErrors++
        else if (it.startsWith("5")) serverErrors++
    }

    println("$clientErrors client errors, $serverErrors server errors")
}

fun lambda_test2() {
    val errors = listOf("403 Forbidden", "404 Not Found", "500 Internal Server Error")
    printProblemCounts(errors)
}