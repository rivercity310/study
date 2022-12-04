package chapter5_Lambda

import chapter1.Person

/* 람다와 컬렉션 */
// - 코틀린에서 함수 호출시 맨 뒤에 있는 인자가 람다 식이라면 그 람다를 괄호 밖으로 뺴낼 수 있다.
// - 만약 람다 식이 함수의 유일한 인자라면 괄호를 생략할 수 있다.

data class Employee(val name: String, val age: Int)

fun lambda1_test1() {

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
// - 람다 안에서 바깥의 변수를 변경할 수 있다. -> "람다가 포획(capture)한 변수" -> closure
// - 포획한 변수가 있는 람다를 저장해서 함수가 끝난 뒤에 실행해도 람다의 본문 코드는 여전히 포획한 변수를 읽거나 쓸 수 있다.
private fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0

    responses.forEach {
        if (it.startsWith("4")) clientErrors++
        else if (it.startsWith("5")) serverErrors++
    }

    println("$clientErrors client errors, $serverErrors server errors")
}

fun lambda1_test2() {
    val errors = listOf("403 Forbidden", "404 Not Found", "500 Internal Server Error")
    printProblemCounts(errors)
}


/* 멤버 참조 */
// - ::를 사용하는 식을 멤버 참조라 부른다. 멤버 참조는 프로퍼티나 메서드를 단 하나만 호출하는 함수 값을 만들어준다.
// - 최상위에 선언된 함수나 프로퍼티를 참조할 때는 ::{이름}처럼 앞을 생략하고 쓴다.

fun Employee.isAdult() = age >= 20          // 확장함수 추가

fun lambda1_test3() {
    val employees = listOf(Employee("seungsu", 26), Employee("suyeoun", 23))

    // 다음 세 식은 모두 같은 동작을 한다.
    println(employees.maxBy { e -> e.age })
    println(employees.maxBy { it.age })
    println(employees.maxBy(Employee::age))

    // "생성자 참조"를 사용하면 클래스 생성 작업을 연기하거나 저장해둘 수 있다.
    val createEmployee = ::Employee     // Employee의 인스턴스를 만드는 동작을 값으로 저장한다.
    val emp1 = createEmployee("Alice", 29)
    println(emp1)

    // 확장함수도 멤버함수와 마찬가지로 참조할 수 있다.
    val adt1 = Employee::isAdult
    println(adt1(emp1))

    val adt2 = emp1::isAdult     // 바운드 멤버 참조
    println(adt2())
}
