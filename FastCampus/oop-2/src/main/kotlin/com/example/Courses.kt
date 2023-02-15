package com.example

// 일급 컬렉션 : 인스턴스 변수로 오직 데이터 객체의 리스트 하나만을 가지는 클래스
class Courses(private val courses: List<Course>) {
    internal fun multiplyCreditAndCourseGrade(): Double =
        courses.sumOf { it.multiplyCreditAndCourseGrade() }

    internal fun totalCompletedCredit(): Int =
        courses.sumOf { it.credit }
}