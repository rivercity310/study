
import java.util.*

class type5 {
    fun test1() {
        // 안전한 호출의 결과 타입도 Nullable
        fun printAllCaps(s: String?) {
            val allCaps: String? = s?.uppercase(Locale.getDefault())
            println(allCaps)
        }

        printAllCaps("abc")
        printAllCaps(null)

        // 프로퍼티를 읽거나 쓸 때도 안전한 호출 사용 가능
        class Employee(val name: String, val manager: Employee?)
        fun managerName(employee: Employee): String? = employee.manager?.name

        val ceo = Employee("Boss", null)
        val dev = Employee("Seungsu", ceo)

        println(managerName(ceo))
        println(managerName(dev))

        // 안전한 호출 연쇄시키기 & 엘비스 연산자
        class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
        class Company(val name: String, val address: Address?)
        class Person(val name: String, val company: Company?)

        fun Person.countryName() = this.company?.address?.country ?: "Unknown"
    }

    fun test2() {
        // 안전한 캐스트 as? -> ClassCastException 방지
        // as? 연산자는 어떤 값을 지정한 타입으로 캐스트, 만약 변환할 수 없으면 null 반환
        class Person(val firstName: String, val lastName: String) {
            override fun equals(o: Any?): Boolean {
                val other = o as? Person ?: return false      // 안전한 캐스트 & 엘비스 연산자 활용
                return this.firstName == other.firstName && this.lastName == other.lastName
            }
        }

        // 널 아님 단언: !! -> 널이 될 수 없는 타입으로 강제 변환
        fun ignoreNulls(s: String?) {
            val sNotNull: String = s!!
            println(sNotNull.length)
        }

        // let 함수 & 안전한 호출 연산자 : 수신 객체가 null -> 호출이 무시되므로 안정적
        // let: 자신의 수신 객체를 인자로 전달받은 람다에게 넘긴다
        fun sendEmailTo(email: String?) = println(email)
        val email1: String? = "hello"
        val email2: String? = null

        email1?.let { email -> sendEmailTo(email) }
        email2?.let { sendEmailTo(it) }

        // 타입 파라미터의 널 가능성
        fun <T> printHashCode(t: T) = println(t?.hashCode())    // t가 null이 될 수 있으므로 안전한 호출
        printHashCode(null)

        fun <T: Any> printHashCode2(t: T) = println(t.hashCode())   // 타입 상한을 지정하여 널이 아님을 확실히 함
        // printHashCode2(null)  -> Error
    }

    fun test3() {
        // String to primitive
        println("42".toInt())
        println("false".toBoolean())
        println("12".toByte())
        println("11".toLong())
    }
}