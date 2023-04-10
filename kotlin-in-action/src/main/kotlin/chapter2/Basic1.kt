package chapter2

import java.util.Random

/* [ 1. 함수, 변수 선언, 문자열 템플릿, 클래스와 프로퍼티 ]*/

/* 파라미터 이름 뒤에 타입 선언 */
fun test1(args: Array<String>) {
    println("Hello, World")
}

/* 리턴값이 있을 땐 반환형을 다음과 같이 선언 -> Kotlin의 if는 statement(문)가 아닌 expression(식) */
// 블럭이 있으면 return 키워드 사용해야함
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

/* 식이 본문인 함수 -> 다음과 같이 간결하게 표현 가능, 반환형까지 생략 가능 */
fun max2(a: Int, b: Int) = if (a > b) a else b


/*
Kotlin의 타입 추론 기능으로 변수를 선언할 때는 타입 지정을 생략하는 경우가 흔하다.

- val : 값을 뜻하는 value에서 따옴, immutable(변경 불가능) 참조를 저장하는 변수 (final) -> 함수형 코드(불변성)를 위해 필요한 경우에만 var를 사용
- var : 변수를 뜻하는 variable에서 따옴, mutable(변경 가능) 참조
* */


/*
문자열 템플릿 : String literal 안에 $를 추가하여 변수를 사용할 수 있다.
- 문자열 + 연산을 통한 String Concatenate 사용과 동일한 기능
- 복잡한 식일 경우 ${} 처럼 중괄호로 둘러싸서 문자열 템플릿 안에 넣을 수 있다. -> 단순 변수 하나를 사용하더라도 웬만하면 중괄호로 감싸도록 하자
*/


/*
클래스
- 코틀린의 default 가시성은 public이고, public은 생략 가능하다.
- 자바에서는 필드와 접근자(getter/setter)를 묶어 프로퍼티라고 부르며, 코틀린은 프로퍼티를 언어 기본 기능으로 제공한다.
- 호출할 때, 프로퍼티 이름을 직접 사용 -> 코틀린이 자동으로 getter/setter를 호출해준다.

class Person {
    val name: String,           // val 선언 : 읽기 전용 프로퍼티 -> 코틀린은 private 필드와 public getter를 만든다.
    var isMarried: Boolean      // var 선언 : 쓸 수 있는 프로퍼티 -> 코틀린은 private 필드와 public getter/setter를 만든다.
}

val person = Person("seungsu", false)   // new 키워드를 사용하지 않고 생성자 호출

println(person.name)                    // 프로퍼티 이름을 직접 사용 -> getter 호출

person.isMarried = true                 // 프로퍼티 이름 직접 사용 -> setter 호출
println(person.isMarried)
 */


/*
커스텀 접근자
- 클라이언트가 프로퍼티에 접근할 때마다 게터가 프로퍼티 값을 매번 다시 계산
*/

class Rectangle(private val height: Int, private val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }

    // 또는
        // get() = height == width
}

fun createRandomRectangle(): Rectangle {
    val random = Random()           // 표준 자바 라이브러리를 사용한다 -> new 선언 제외
    return Rectangle(random.nextInt(), random.nextInt())
}

fun basic1() {
    println(createRandomRectangle().isSquare)
}