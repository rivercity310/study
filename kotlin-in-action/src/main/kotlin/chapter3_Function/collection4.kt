package chapter3_Function

import java.lang.NullPointerException

// 1. 문자열과 정규식 다루기
// 자바 String의 split 메서드는 regex를 인자로 받아 String 배열을 반환한다.
// 코틀린에서는 regex와 일반 구분 문자열 둘다 문자열을 분해할 수 있다.

fun parsePath(path: String) {
    // /Users/yole/kotlin-book/chapter.adoc

    val directory = path.substringBeforeLast("/")   // 마지막 "/" 바로 전까지 -> /Users/yole/kotlin-book
    val fullName = path.substringAfterLast("/")     // chater.adoc

    val fileName = fullName.substringBeforeLast(".")    // chater
    val extension = fullName.substringAfterLast(".")    // adoc

    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePathWithRegex(path: String) {
    val regex: Regex = """(.+)/(.+)\.(.+)""".toRegex()         // 3중 따옴표 -> 어떤 문자도 이스케이프할 필요 없음
    val matchResult: MatchResult? = regex.matchEntire(path)
    val (directory, filename, extension) = matchResult?.destructured ?: throw NullPointerException()
    println("Dir: $directory, name: $filename, ext: $extension")
}

fun collection4() {
    println("12.3345-6.A".split("\\.|-".toRegex()))
    println("12.3345-6.A".split(".", "-"))

    parsePathWithRegex("Users/yole/kotlin-book/chapter.adoc")
}