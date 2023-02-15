package com.example

class GradeCalculator(_courses: List<Course>) {
    private val courses = Courses(_courses)

    fun calculateGrade(): Double {
        val mul = courses.multiplyCreditAndCourseGrade()
        val totalCredit = courses.totalCompletedCredit()
        return mul / totalCredit
    }

}
