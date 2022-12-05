package chapter6_kotlin_type_system


// 코틀린의 타입 시스템은 몇가지 특성을 새로 제공한다 (nullable type, 읽기 전용 컬렉션)


// 1. 널 가능성(nullability): NullPointerException(NPE) 오류를 피할수 있게 돕는 코틀린의 특성이다.
// 어떤 변수가 nul이 될 수 있다면 그 변수에 대해 메서드를 호출했을 때 NPE가 발생할 수 있으므로 안전하지 않다.
// 따라서 프로퍼티나 변수에 null을 허용하게 만드는 방법이 nullable type이다.
// 코틀린을 비롯한 최신 언어에서 null에 대한 접근 방법은 가능한 한 이 문제를 실행 시점에서 컴파일 시점으로 옴기는 것이다. (컴파일 시점에 처리)

// ex)
/*
아래 함수는 안전하지 않다. s에 null을 넘기면 NPE가 발생한다.

int strlen(String s) {
    return s.length();
}
*/

// nullable type은 메서드를 직접 호출할 수도, 널이 될 수 있는 타입의 변수에 직접 대입할 수도, 널이 될수 없는 타입의 파라미터를 받는 함수에 전달할 수도 없다.
// if 검사를 통해 널 가능성을 다룰 수 있다 (컴파일러가 null과 비교한 사실을 기억하고 null이 아님이 확실한 영역에서는 해당 값을 널이 될 수 없는 타입의 값처럼 사용한다)
fun strLenSafe(s: String?): Int =
    if (s != null) s.length else 0



// [ 널이 될 수 있는 값을 안전하게 다루게 도와주는 특별한 연산자 ]

// 1. 안전한 호출 연산자: ?.
// - 호출하려는 값이 null이 아니라면 ?.은 일반 메서드 호출처럼 작동하고, null이면 이 호출은 무시되고 null이 결과값이 된다.
// - 즉 안전한 호출의 결과 타입도 nullable type이다.
// ex) s?.toUpperCase() = if (s != null) s.toUpperCase() else null 은 서로 같다


// 메서드 호출뿐 아니라 프로퍼티를 읽거나 쓸 때도 안전한 호출을 사용할 수 있다.
class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name

fun nullable_test1() {
    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob Smith", ceo)

    println(managerName(developer))
    println(managerName(ceo))       // null
}


// 안전한 호출 연쇄시키기

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
    val country = this.company?.address?.country
    return country ?: "Unknown"
}

fun nullable_test2() {
    val person = Person("Dmitry", null)
    println(person.countryName())
}




// 엘비스 연산자: ?:
// null 대신 사용할 디폴트 값을 지정할 때 사용
fun foo(s: String?) {
    val t: String = s ?: ""     // s가 null이면 결과는 ""
}


// 엘비스 연산자를 안전한 호출 연산자(?.)와 함께 사용 -> 객체가 널인 경우에 대비한 값을 지정
fun strLenSafe2(s: String?) = s?.length ?: 0
fun Person.countryName2() = company?.address?.country ?: "Unknown"



// 엘비스 연산자 우항에 return, throw 등의 연산을 넣어 좌항이 널이면 함수가 즉시 어떤 값을 반환하거나 예외를 던지게 하는 패턴
private fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No Address")

    with (address) {    // address는 null이 아니다.
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

fun nullable_test3() {
    val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val person = Person("Dmitry", jetbrains)

    printShippingLabel(person)
    printShippingLabel(Person("Alexey", null))      // 엘비스 연산자 우항에 정의된 예외를 던진다
}






