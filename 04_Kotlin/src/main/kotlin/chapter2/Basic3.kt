package chapter2

import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException
import java.util.*

// [ 3. Iteration, 예외 처리 ]

// while loop (while, do-while) : 자바와 동일
// for loop : for-each 형태만 존재

// 코틀린의 범위(range)는 양 끝을 포함하는 폐구간이고 .. 연산자로 시작값과 끝값을 연결해서 범위를 만든다
// ex) val a = 1..10

private fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

private fun fizzBuzzGame() {
    for (i in 1..15)
        print(fizzBuzz(i))

    println("\n")

    // 100부터 거꾸로 짝수만
    for (i in 100 downTo 1 step 2)
        print(fizzBuzz(i))

    println("\n")

    // until 함수는 반만 닫힌 구간 범위를 만들 때 사용
    for (x in 0 until 15)
        print(fizzBuzz(x))
}


// 맵에 대한 이터레이션

// - 문자에 대한 2진 표현을 출력하는 함수
private fun IterForMap() {
    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {      // .. 연산자는 문자 타입에도 적용 가능
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps)   // 맵에 대한 구조 분해
        println("$letter = $binary")
}

private fun IterForMap2() {
    val list = arrayListOf("10", "11", "1001")

    for ((index, element) in list.withIndex())
        println("$index: $element")
}



// in 키워드로 어떤 값이 범위나 컬렉션에 존재하는지 판별할 수 있다
// 범위는 비교 가능한 클래스라면(Comparable 인터페이스를 구현한 클래스) 그 클래스의 인스턴스 객체를 사용하여 만들 수 있다 -> String 등등
private fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
private fun isNotDigit(c: Char) = c !in '0'..'9'
private fun inKeywordTest() {
    val letter = 'q'
    val digit = '9'

    println("$letter is Letter? ${isLetter(letter)}")
    println("$digit is not Digit? ${isNotDigit(digit)}")
    println("$digit is Digit? ${!isNotDigit(digit)}")
    println("$letter is Not Digit? ${isNotDigit(letter)}")
}

private fun inKeywordTestInWhen(c: Char) = when(c) {
    in '0'..'9' -> "It's digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter"
    else -> "I don't know"
}



// 예외 처리
// 코틀린의 try 키워드는 식이다
private fun readNumber(reader: BufferedReader) {
    val number = try { Integer.parseInt(reader.readLine()); } catch (e: NumberFormatException) { null }
    println(number ?: -1)
}

fun basic3_ex1() {
    readNumber(BufferedReader(StringReader("abc")))
    readNumber(BufferedReader(StringReader("12345")))
}


private class Test(private val t1: Int, val t2: Int, var t3: Int)

fun basic3_ex2() {
    val t = Test(1, 2, 3)
    // t.t1  --> getter 접근 불가 (private getter)
    println(t.t2)
    println(t.t3)

    // t.t2 = 4  --> setter 접근 불가 (val)
    t.t3 = 4
    println(t.t3)
}