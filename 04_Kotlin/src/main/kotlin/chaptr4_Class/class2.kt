package chaptr4_Class

import javax.naming.Context
import javax.swing.text.AttributeSet

// 1. 코틀린의 생성자

// - 주 생성자: 클래스 이름 뒤에 오는 괄호로 둘러싸인 코드
open class User(private val nickName: String = "Seungsu", private val isSubscribed: Boolean = false)    // 생성자 파라미터에도 디폴트 값을 정의할 수 있음

// - 위 선언을 풀어서 쓰면 다음 코드와 같다
open class User2 constructor(_nickName: String = "seungsu", _isSubscribed: Boolean = false) {
    private val nickName: String
    private val isSubscribed: Boolean

    init {
        nickName = _nickName
        isSubscribed = _isSubscribed
    }
}

// - 기반 클래스의 생성자를 호출하여 기반 클래스를 초기화
// - 클래스를 상속받는 경우엔 기반 클래스를 반드시 초기화해야 하기때문에 뒤에 괄호가 들어가지만, 인터페이스는 생성자가 없기 때문에 괄호가 들어가지 않음
class TwitterUser(nickName: String): User(nickName) {}

// - 만약 어떤 클래스를 클래스 외부에서 인스턴스화 하지 못하게 막으려면 모든 생성자를 private으로 만든다
class Secretive private constructor() {}


// - 부 생성자 : 디폴트 파라미터와 이름붙인 인자를 통해 웬만하면 대체 가능하므로 거의 사용할 일 없음
// - 클래스에 주 생성자가 없다면, 부 생성자는 반드시 상위 클래스를 초기화하거나 다른 생성자에게 생성을 위임해야 한다.

// 부 생성자를 통해 파라미터 목록이 다른 생성 방법을 제공하는 예제
open class View2 {
    constructor(ctx: Context) {}
    constructor(ctx: Context, attr: String)
}

class MyButton: View2 {
    constructor(ctx: Context): this(ctx, "MY STYLE") {}         // this: 다른 생성자에게 생성 위임
    constructor(ctx: Context, attr: String): super(ctx, attr) {}     // super: 부모 생성자에게 생성 위임
}








// 2. 인터페이스에 선언된 프로퍼티 구현
// - 코틀린에서는 인터페이스에 "추상 프로퍼티" 선언을 넣을 수 있다
interface User3 {
    val nickname: String        // User3 인터페이스를 구현하는 클래스는 nickname의 값을 얻을 수 있는 방법을 제공해야 함
}

// - 아래 세 클래스는 각각 다른 방식으로 추상 프로퍼티 nickname을 구현하는 예시

class PrivateUser(override val nickname: String): User3 {}      // - 주 생성자

class SubscribingUser(val email: String): User3 {               // - 커스텀 게터
    override val nickname: String
        get() = email.substringBefore("@")
}

class FacebookUser(val accountId: Int): User3 {                 // - 프로퍼티 초기화식
    override val nickname = getFacebookName(accountId)
}

fun getFacebookName(accountId: Int): String = "Hi"

// SubscribingUser와 FacebookUser의 nickname 구현 차이!!
// - SubscribingUser: 매번 호출될 때마다 커스텀 게터가 호출되어 nickname이 계산됨
// - FacebookUser: 객체 초기화 시 한번만 계산하여 필드에 저장







// 3. 게터와 세터에서 뒷받침하는 필드에 접근
// - 위에서 "값을 저장하는 프로퍼티"와 커스텀 접근자에서 "매번 값을 계산하는 프로퍼티"에 대해 살펴보았다.
// - 두 유형을 조합해서 어떤 값을 저장하되 그 값을 변경하거나 읽을 때마다 정해진 로직을 실행하는 유형의 프로퍼티를 만들 수 있다

// ex) 프로퍼티에 저장된 값의 변경 이력을 로그에 남기는 경우, 세터에서 뒷받침하는 필드 접근
class User4(val name: String) {
    var address: String = "unspecified"
        set(value: String) {                        // field라는 특별한 식별자를 통해 뒷받침하는 필드에 접근할 수 있다.
            println("Address was changed for $name: $field -> $value".trimIndent())
            field = value
        }
}

fun class2_test1() {
    val user = User4("Alice")
    user.address = "Elsenheimerstrasse 47"      // address의 setter 자동 호출
    user.address = "Rivercity 310-1502"

    /*
    [ 실행 결과 ]
    Address was changed for Alice: unspecified -> Elsenheimerstrasse 47
    Address was changed for Alice: Elsenheimerstrasse 47 -> Rivercity 310-1502
    */
}





// 4. 원한다면 접근자의 가시성도 변경할 수 있다.

// ex) 비공개 세터가 있는 프로퍼티 선언하기: 프로퍼티를 외부에 공개하되(public),
// 외부 코드에서 변경하지 못하도록 세터의 가시성을 private으로 변경
class LengthCounter {
    var counter: Int = 0        // 이 클래스 밖에서 이 프로퍼티의 값을 변경할 수 없다
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}