package chapter7_operator_overloading


// [ 위임 프로퍼티 컴파일 규칙 ]
// 1. 컴파일러는 MyDelegate 클래스의 인스턴스를 감춰진 프로퍼티에 저장 (<delegate>로 표현)
// 2. 컴파일러는 프로퍼티를 표현하기 위해 KProperty 타입의 객체를 사용 (<property>로 표현)
// -> 이 메커니즘을 이용해 다양한 활용이 가능하다 (값 검증, 변경 통지 또는 맵, DB, 쿠키 등에 프로퍼티 저장 등)

/*
class C {
    private val <delegate> = MyDelegate()
    var prop: Type
        get() = <delegate>.getValue(this, <property>)
        set(value: Type) = <delegate>.setValue(this, <property>, value)
}
*/


// - 프로퍼티 값을 맵에 저장
// 자신의 프로퍼티를 동적으로 정의할 수 있는 객체를 만들 때 위임 프로퍼티를 활용 (확장 가능한 객체)

class Person5 {
    // 추가 정보
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    // 필수 정보
    val name: String by _attributes     // 위임 프로퍼티로 맵을 사용
}

fun overload4_test1() {
    val p = Person5()
    val data = mapOf("name" to "Dmitry", "company" to "JetBrains")

    for ((attrName, value) in data) p.setAttribute(attrName, value)
    println(p.name)
}



