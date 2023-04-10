package chapter8_higher_order_function

// 1. 함수에서 함수 반환
// 함수를 반환하려면 return 식에 람다나 멤버 참조 혹은 함수 타입의 값을 계산하는 식 등을 넣으면 된다.
private enum class Delivery { STANDARD, EXPEDITED }
private class Order(val itemCount: Int)

private fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) return { order -> 6 + 2.1 * order.itemCount }
    return { order -> 1.2 * order.itemCount }
}

fun hof2_ex1() {
    val calc = getShippingCostCalculator(Delivery.EXPEDITED)
    println("Shipping costs ${calc(Order(3))}")
}


// 함수를 반환하는 함수가 유용한 경우 예시

// - 입력 문자열과 매치되는 연락처만 화면에 표시
private data class Person(val firstName: String, val lastName: String, val phoneNumber: String?)

private class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }

        if (!onlyWithPhoneNumber) return startsWithPrefix
        return { startsWithPrefix(it) && it.phoneNumber != null }
    }
}

fun hof2_ex2() {
    val contacts = listOf(Person("Dmitry", "Jemerov", "123-4567"), Person("Svetlana", "Isakova", null))
    val contactListFilters = ContactListFilters()
    with (contactListFilters) {
        prefix = "Dm"
        onlyWithPhoneNumber = true
    }

    println(contacts.filter(contactListFilters.getPredicate()))
}


// 2. 람다를 활용한 중복 제거

// - 웹사이트 방문 기록을 분석하는 예시
private enum class OS { WINDOWS, LINUX, MAC, IOS }
private data class SiteVisit(val path: String, val duration: Double, val os: OS)

// 중복을 피하기 위한 확장함수 정의
private fun List<SiteVisit>.averageDurationFor(os: OS) =
    filter { it.os == os }.map(SiteVisit::duration).average()

// 더 복잡한 질의를 위한 고차 함수 정의
private fun List<SiteVisit>.averageDurationFor2(predicate: (SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()

fun hof2_ex3() {
    val log = listOf(
        SiteVisit("/", 34.0, OS.WINDOWS),
        SiteVisit("/", 22.0, OS.MAC),
        SiteVisit("/login", 12.0, OS.WINDOWS),
        SiteVisit("/signup", 8.0, OS.IOS),
        SiteVisit("/", 16.3, OS.LINUX)
    )

    // 윈도우 사용자의 평균 방문 시간
    val averageWindowsDuration = log
        .filter { it.os == OS.WINDOWS }
        .map(SiteVisit::duration)
        .average()

    println(averageWindowsDuration)

    // 맥 사용자 평균 시간
    println(log.averageDurationFor(OS.MAC))

    // 고차 함수를 사용해 중복 제거 & 복잡한 질의 대응
    println(log.averageDurationFor2 { it.os in setOf(OS.MAC, OS.LINUX) })
    println(log.averageDurationFor2 { it.os == OS.IOS && it.path == "/signup" })
}