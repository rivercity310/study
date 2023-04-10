package com.example

data class Course(
    val subject: String,
    val credit: Int,
    val grade: String
) {
    private fun gradeToNumber(): Double = when (grade) {
        "A+" -> 4.5
        "A" -> 4.0
        "B+" -> 3.5
        "B" -> 3.0
        "C+" -> 2.5
        "C" -> 2.0
        else -> 0.0
    }

    internal fun multiplyCreditAndCourseGrade(): Double =
        credit * gradeToNumber()


}

