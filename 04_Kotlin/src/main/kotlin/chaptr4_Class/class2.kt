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