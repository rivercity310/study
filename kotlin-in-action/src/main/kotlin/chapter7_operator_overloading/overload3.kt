package chapter7_operator_overloading

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// [ 프로퍼티 접근자 로직 재활용: 위임 프로퍼티 ]
// - 위임 프로퍼티를 사용하면 값을 뒷받침하는 필드에 단순히 저장하는 것보다 더 복잡한 방식으로 작동하는 프로퍼티를 쉽게 구현할 수 있다.
// ex) 프로퍼티는 위임을 사용해 자신의 값을 필드가 아닌 DB 테이블이나 브라우저 세션, 맵 등에 저장할 수 있다.

// 위임: 객체가 직접 작업을 수행하지 않고 다른 도우미 객체(위임 객체)가 그 작업을 처리하게 맡기는 디자인 패턴

// - 지연 초기화: 객체의 일부분을 초기화하지 않고 남겨두었다가 실제로 그 부분의 값이 필요할 때 초기화하는 패턴
// 1. 뒷받침하는 프로퍼티(backing property)를 통해 지연 초기화 구현
class Email {}

fun loadEmail(person: MyPerson): List<Email>? {
    println("${person.name}의 이메일을 가져옵니다")
    return listOf(Email())
}

class MyPerson(val name: String) {
    /*
    private var _emails: List<Email>? = null        // 데이터를 저장하고 emails의 위임 객체 역할을 하는 프로퍼티
    val emails: List<Email>     // _emails에 대한 읽기 연산 제공
        get() {
            if (_emails == null) _emails = loadEmail(this)      // 최초 접근시에만 이메일을 가져옴
            return _emails!!
        }
    */

    // 위 코드를 위임 프로퍼티를 사용하여 단순화 -> 데이터를 저장할 때 쓰이는 뒷받침하는 프로퍼티와 값이 오직 한번만 초기화됨을 보장하는 게터 로직을 함께 캡슐화
    val emails by lazy { loadEmail(this) }
}

fun overload3_test1() {
    val p = MyPerson("Alice")
    p.emails
    p.emails
}


open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

// 이 클래스는 나이나 급여가 바뀌면 그 사실을 리스너에게 통지한다.
class MyPerson2(val name: String, age: Int, salary: Int): PropertyChangeAware() {
    /* [ Refactoring 1 ]
    var age: Int = age
        set(newValue) {
            val oldValue = field        // 뒷받침하는 필드에 접근할 때 field 식별자 사용
            field = newValue
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
     */

    /* [ Refactoring 2 ]
    val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) { _age.setValue(value) }

    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) { _salary.setValue(value) }
     */

    /*
    var age: Int by ObservableProperty(age, changeSupport)      // 위임 객체를 감춰진 프로퍼티에 저장하고, 주 객체의 프로퍼티를 읽거나 쓸 때마다 위임 객체의 getter/setter 호출
    var salary: Int by ObservableProperty(salary, changeSupport)
    */

    // 코틀린 표준 라이브러리 사용 -> PropertyChangeSupport와 연결돼 있지 않으므로 사용하는 방법을 알려주는 람다를 넘겨주어야 한다.
    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int -> changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

/*
class ObservableProperty(var propValue: Int, val changeSupport: PropertyChangeSupport) {
    operator fun getValue(p: MyPerson2, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: MyPerson2, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}
*/

fun overload3_test2() {
    val p = MyPerson2("Dmitry", 34, 2000)
    p.addPropertyChangeListener(PropertyChangeListener { event ->
        println("Property ${event.propertyName} changed " + "from ${event.oldValue} to ${event.newValue}")
    })

    p.age = 35
    p.salary = 2100
}
