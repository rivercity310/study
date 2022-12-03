package chapter4_Class

/* [ 모든 클래스가 정의해야 하는 메서드: toString, equals, hashCode ] */
// - 코틀린에서는 이런 메서드 구현을 자동으로 생성해주지만 오버라이드해서 기본 구현을 바꿀 수 있다.
// - 코틀린에서 객체비교(==)는 참조 동일성이 아닌 객체 동등성을 검사한다. 따라서 == 연산은 equals를 호출하는 식으로 컴파일된다. 반면 참조 비교를 하려면 === 연산자를 사용한다.
// - 자바에서는 ==는 원시 타입의 두 값이 같은지 동등성을 비교하고, 참조 타입의 경우 ==는 주소가 같은지 비교한다. 따라서 자바에서 두 객체의 동등성 비교를 하려면 equals를 호출해야 한다.
// - 자바에서는 equals를 오버라이드할 때 반드시 hashCode도 함께 오버라이드 해야한다.
// - "JVM 언어에서 equals()가 true를 반환하는 두 객체는 반드시 같은 hashCode()를 반환해야 한다"
// - ex) HashSet은 원소를 비교할 때 비용을 줄이기 위해 먼저 객체의 해시 코드를 비교한 뒤, 해시 코드가 같은 경우에만 실제 값을 비교한다.


/* data class에서 코틀린 컴파일러가 자동으로 구현해주는 메서드들을 직접 구현해본 클래스 */
private class Client(val name: String, val postalCode: Int) {
    override fun toString(): String = "Client(name=$name, postalCode=$postalCode)"
    override fun equals(other: Any?): Boolean {           // Any는 java.lang.Object에 대응하는 클래스로, 코틀린의 모든 클래스의 최상위 클래스이다.
         if (other == null || other !is Client)           // is 연산자는 자바의 instanceOf와 같다.
             return false

        return name == other.name && postalCode == other.postalCode
    }

    override fun hashCode(): Int = name.hashCode() * 31 + postalCode

    fun copy(name: String = this.name, postalCode: Int = this.postalCode) = Client(name, postalCode)
}


fun class3_test() {
    val client1 = Client("황승수", 41234)
    println(client1)

    val client2 = Client("황승수", 41234)
    println(client1 == client2)     // true
}


/* 데이터 클래스: 모든 클래스가 정의해야 하는 메서드(위에서 정의한) 자동 생성 */
// - data라는 변경자를 클래스 앞에 붙이면 필요한 메서드를 컴파일러가 자동으로 만들어준다 (equals, hashCode, toString, copy 등)
// - data class의 모든 프로퍼티를 읽기 전용(val)로 만들어서 immutable class로 만들자
// - 불변성을 지키기 위해 객체를 직접 바꾸는 대신 복사본을 만들면서 일부 프로퍼티를 바꿀 수 있게 해주는 copy 메서드가 제공된다.

private data class Client2(val name: String, val postalCode: Int)

fun class3_test2() {
    val hwang = Client2("황승수", 41324)
    val tmp = hwang.copy(postalCode = 40000)
    println(hwang == tmp)
    println(tmp)
}


/* 클래스 위임: by 키워드 */
// - 상속을 허용하지 않는 클래스에 새로운 동작을 추가해야할 때 데코레이터 패턴을 사용한다
// - 데코레이터 패턴은 새로운 클래스(데코레이터)를 만들되 기존 클래스와 같은 인터페이스를 데코레이터가 제공하게 만들고, 기존 클래스를 데코레이터 내부에 필드로 유지하는 것이다.
// - 이때 새로 정의해야 하는 기능은 데코레이터의 메서드에 새로 정의하고, 기존 기능이 그대로 필요한 부분은 데코레이터의 메서드가 기존 클래스의 메서드에게 요청을 전달한다.

/* 단순한 데코레이터를 구현해보자 */
private class DelegatingCollection<T>: Collection<T> {
    private val innerList = arrayListOf<T>()

    override val size: Int get() = innerList.size
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun iterator(): Iterator<T> = innerList.iterator()
    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)

}

// - 코틀린에서는 위와 같은 위임을 언어가 제공하는 일급 시민 기능으로 지원한다
// - 다음은 앞의 예제를 위임을 사용해 재작성한 것이다.
class DelegatingCollection2<T>(private val innerList: Collection<T> = ArrayList<T>()): Collection<T> by innerList { }


// - 위 기법을 이용하여 원소를 추가하려고 시도한 횟수를 기록하는 컬렉션을 구현해보자
// - 메서드 중 일부 동작을 변경하고 싶은 경우 다음과 같이 필요한 메서드만 오버라이드 한다.
// - add와 addAll 메서드는 오버라이드하여 위임하지 않고 새로운 구현을 제공한다.
private class CountingSet<T>(private val innerSet: MutableCollection<T> = HashSet<T>()): MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun class3_test3() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 1, 2))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}