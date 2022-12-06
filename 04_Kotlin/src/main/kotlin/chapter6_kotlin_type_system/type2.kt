package chapter6_kotlin_type_system


// 널 아님 단언: !!
// - 널이 될 수 있는 타입의 인자를 널이 될 수 없는 타입으로 변환, null을 변환하려 하면 NPE 발생
fun ignoreNulls(s: String?) {
    val sNotNull = s!!          // 실제 NPE 발생 지점. 아래가 아니라 여기서 예외가 발생한다.
    println(sNotNull.length)
}

fun type2_test1() {
    ignoreNulls("seungsu")
    // ignoreNulls(null)   -> NullPointerException
}



// let 함수
// - 널이 될 수 있는 값을 널이 아닌 값만 인자로 받는 함수에 넘길 때

fun sendEmailTo(email: String) {}

fun type2_test2() {
    val email: String? = ""
    if (email != null) sendEmailTo(email)           // 검사를 통해야만 인자로 넘길 수 있다.

    // let을 안전한 호출 구문을 통해 호출하면 람다로 널이 될 수 없는 타입으로 바꿔서 전달
    email?.let { email -> sendEmailTo(email) }
    email?.let { sendEmailTo(it) }      // it를 사용하면 더 짧게 사용 가능
}



// 나중에 초기화할 프로퍼티
// - 코틀린에서는 일반적으로 생성자에서 모든 프로퍼티를 초기화해야 한다.
// - nullable type으로 선언하고 프로퍼티를 null로 초기화하게 되면 모든 프로퍼티 접근에 널 검사나 !! 연산자를 써야한다는 불편함이 있다.

class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    private lateinit var myService: MyService     // 초기화 없이 널이 될 수 없는 프로퍼티 선언

    fun setUp() {
        myService = MyService()
    }
}



// 널이 될 수 있는 타입 확장
// - nullable type에 대한 확장 함수를 정의하여 null 값을 다루는 도구로 활용할 수 있다.

// nullable type 수신 객체에 대해 확장 함수 호출 (isNullOrBlank(), isNullOrEmpty() 등)
// - ex) isNullOrBlank() 함수는 null이면 true를 반환하고, null이 아니면 isBlank()를 호출한다.
// - 따라서 안전한 호출 없이도 확장 함수 호출이 가능하다.
fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank())      // 안전한 호출을 하지 않아도 된다.
        println("Please fill in the required fields")
}



// 타입 파라미터의 널 가능성, 기본적으로 T의 타입은 Any?로 추론
fun <T: Any> printHashCode(t: T) {      // 타입 파라미터에 대해 타입 상한을 지정 -> 널이 될 수 없음
    println(t.hashCode())
}

fun type2_test3() {
    // printHashCode(null) -> 타입 상한에 의해 호출 불가
}




// 널 가능성과 자바
// 자바의 @Nullable String은 코틀린에서 String?과 같고, @NotNull String은 String과 같다.
// 만약 널 가능성 Annotation이 없으면 자바의 타입은 코틀린의 플랫폼 타입(Platform type)이 된다.

// - Platform Type: 코틀린이 널 관련 정보를 알 수 없는 타입
// 그 타입(String)을 널이 될 수 있는 타입(String?)으로 또는 널이 될 수 없는 타입(String)으로 처리해도 되지만 NPE의 위험을 막아주지 못한다.

// 널 가능성 Annotation이 없는 자바 클래스
/*
public class TestPerson {
    private final String name;

    public TestPerson(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
*/
