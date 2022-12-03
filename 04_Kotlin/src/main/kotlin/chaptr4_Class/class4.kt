package chaptr4_Class

import java.io.File

/* object 키워드: 클래스 선언과 인스턴스 생성 */

/* 객체 선언(object 키워드): 싱글턴을 쉽게 만들기 */
// - 자바에서는 보통 클래스의 생성자를 private으로 제한하고, 정적인 필드에 그 클래스의 유일한 객체를 저장하는 싱글턴 패턴을 통해 인스턴스가 하나만 필요한 클래스를 구현한다.
// - 코틀린은 객체 선언 기능을 통해 싱글턴을 언어에서 기본 지원한다.

/* 두 파일 경로를 대소문자 관계없이 비교해주는 Comparator */
private object CaseInsensitiveFileComparator: Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

/* 클래스 안에서 객체를 선언할 수도 있다. */
// - 바깥 클래스의 인스턴스마다 중첩 객체 선언에 해당하는 인스턴스가 하나씩 따로 생기는 것이다.
private data class Person(val name: String) {
    object NameComparator: Comparator<Person> {
        override fun compare(p0: Person, p1: Person): Int = p0.name.compareTo(p1.name)
    }
}


fun class4_test() {
    println(CaseInsensitiveFileComparator.compare(File("/BUser"), File("/Auser")))

    // 일반 객체를 사용할 수 있는 곳에서는 항상 싱글톤 객체를 사용할 수 있다.
    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))

    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.NameComparator))
}


/* 동반 객체(companion): 팩토리 메서드와 정적 멤버가 들어갈 장소 */
// - 코틀린 클래스 안에는 정적인 멤버가 없다. (자바 static 키워드 지원 X)
// - 그 대신 코틀린에서는 최상위 함수(자바의 정적 메서드 역할을 대신)와 객체 선언(자바의 정적 필드나 그 외 역할을 대신)을 활용한다
// - 다만, 팩토리 메서드와 같이 클래스의 인스턴스와 관계없이 호출해야 하지만, 클래스 내부 정보(private)에 접근해야 하는 함수가 필요할 때 동반 객체를 사용한다.

private class MyUser {
    val nickname: String

    // 부 생성자가 여럿 있는 클래스 정의
    constructor(email: String) { nickname = email.substringBefore("@") }
    constructor(facebookAccountId: Int) { nickname = getFacebookName(facebookAccountId) }
}

// 위와 같은 로직을 클래스의 인스턴스를 생성하는 팩토리 메서드를 통해 더 유용하게 표현할 수 있다.
private class MyUser2 private constructor(val nickname: String) {      // 주 생성자를 비공개로 만든뒤 동반 객체를 통해 인스턴스를 만든다
    companion object {
        fun newSubscribingUser(email: String) = MyUser2(email.substringBefore("@"))
        fun newFacebookUser(facebookAccountId: Int) = MyUser2(getFacebookName(facebookAccountId))
    }
}

fun class4_test2() {
    val subscribingUser = MyUser2.newSubscribingUser("bob@gmail.com")
    val facebookUser = MyUser2.newFacebookUser(4)
    println(subscribingUser.nickname)
    println(facebookUser.nickname)
}


/* 동반 객체를 일반 객체처럼 사용 */
// - 동반 객체는 클래스 안에 정의된 일반 객체다. 따라서 이름을 붙이거나 인터페이스를 상속하거나 확장 함수와 프로퍼티를 정의할 수 있다
// - 동반 객체의 이름을 따로 지정하지 않으면 객체 이름은 자동으로 Companion이 된다. 자바 쪽에서도 이 이름으로 동반 객체의 참조에 접근한다

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

private class People(val name: String) {
    companion object Loader: JSONFactory<People> {
        override fun fromJSON(jsonText: String): People = People(jsonText.substringAfter("'").substringBefore("'"))
    }
}

fun class4_test3() {
    val person = People.Loader.fromJSON("{name: 'Dmitry'}")
    println(person.name)
}
