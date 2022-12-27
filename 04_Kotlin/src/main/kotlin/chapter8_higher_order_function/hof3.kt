package chapter8_higher_order_function

class Hof3 {
    // 1. 함수를 반환하는 고차함수 정의해보기
    enum class Delivery { STANDARD, EXPEDITED }
    class Order(val itemCount: Int)
    private fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
        if (delivery == Delivery.STANDARD) return { order -> 6 + 2.1 * order.itemCount }
        return { order -> 1.2 * order.itemCount }
    }

    fun test1() {
        val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
        println(calculator(Order(3)))
    }


    // 2. 고차함수를 활용한 중복 제거
    enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }
    data class SiteVisit(val path: String, val duration: Double, val os: OS)
    private fun List<SiteVisit>.getAverageDuration(predicate: (SiteVisit) -> Boolean) = filter(predicate).map(SiteVisit::duration).average()

    fun test2() {
        val log = listOf(
            SiteVisit("/", 34.0, OS.WINDOWS),
            SiteVisit("/", 22.0, OS.MAC),
            SiteVisit("/login", 12.0, OS.WINDOWS),
            SiteVisit("/signup", 8.0, OS.IOS),
            SiteVisit("/", 16.3, OS.ANDROID),
            SiteVisit("/login", 13.4, OS.LINUX)
        )

        println(log.getAverageDuration { it.os == OS.IOS && it.path == "/signup" })
        println(log.getAverageDuration { it.os == OS.WINDOWS || it.path == "/login" })
    }
}