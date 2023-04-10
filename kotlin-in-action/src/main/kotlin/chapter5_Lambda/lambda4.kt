package chapter5_Lambda

/* 자바 함수형 인터페이스 활용 */
// - 코틀린의 람다를 자바 API에 사용해도 아무 문제가 없다.
// * SAM(Single Abstract Method, 단일 추상 메서드) 인터페이스 or 함수형 인터페이스 -> OnclickListener, Runnable, Callable 등 추상 메서드가 하나만 있는 인터페이스
// - 코틀린은 SAM 인터페이스를 인자로 취하는 자바 메서드를 호출할 때 람다를 넘길 수 있게 해준다.
// - 따라서 무명 클래스 인스턴스를 만들 필요가 없다.



/* 수신 객체 지정 람다: with, apply */
// 수신 객체를 명시하지 않고 람다의 본문 안에서 다른 객체의 메서드를 호출할 수 있게 해준다

fun alphabet(): String {
    val result = StringBuilder()

    for (letter in 'A'..'Z')
        result.append(letter)

    result.append("\nNow I Know the alphabet!")
    return result.toString()
}


// with문은 실제로 파라미터가 2개(수신객체, 람다) 있는 함수다. 람다를 괄호 밖으로 빼는 관례를 사용함에 따라 아래와 같은 모양을 한다
// with 함수는 첫번째 인자로 받은 객체를 두번째 인자로 받은 람다의 수신 객체로 만든다.
fun alphabet_with(): String = with(StringBuilder()) {        // with에 메서드를 호출하려는 수신 객체를 지정
        for (letter in 'A'..'Z')                                   // this를 명시하거나 생략해서 수신 객체의 메서드를 호출
            this.append(letter)

        append("\nNow I Know the alphabet")
        toString()
    }


// buildString을 사용하여 더 단순화시키기
// - StringBuilder 객체를 만드는 일과 toString을 호출해주는 일을 알아서 해주는 메서드
// - buildString의 인자는 수신 객체 지정 람다이고, 수신 객체는 항상 StringBuilder가 된다.
fun alphabet_bs() = buildString {
    for (letter in 'A'..'Z') append(letter)
    append("\nNow I Know the alphabet")
}




// apply: 거으 with와 같지만 apply는 항상 자신에게 전달된 객체(수신 객체)를 반환한다.
// - apply는 확장 함수로 정의돼 있다. apply 함수는 객체의 인스턴스를 만들면서 즉시 프로퍼티 중 일부를 초기화해야 하는 경우에 유용하다.
fun alphabet_apply() = StringBuilder().apply {
    for (letter in 'A'..'Z')
        append(letter)
    append("\nNow I Know the alphabet")
}.toString()


/*
// [ apply로 프로퍼티 초기화하기 ]
// 1. 새로운 TextView 인스턴스를 만들고 즉시 그 인스턴스를 apply에 넘긴다.
// 2. 전달된 람다 안에서는 TextView를 초기화하고, apply는 람다에 의해 초기화된 TextView 인스턴스를 반환한다.
// 3. 그 인스턴스가 함수의 결과가 된다.

fun createViewWithCustomAttributes(context: Context) = TextView(context).apply {
    text = "Sample Text"
    textSize = 20.0
    setPadding(10, 0, 0, 0)
}
*/



