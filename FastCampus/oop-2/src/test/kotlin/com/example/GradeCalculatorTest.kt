package com.example

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class GradeCalculatorTest {
    // 이수한 과목을 전달하여 평균 학점 계산

    @DisplayName("평균 학점 계산")
    @Test
    fun calculateGradeTest() {
        val courses = listOf(
            Course("OOP", 3, "A+"),
            Course("자료구조", 3, "A+"),
            Course("역사와 문화", 2, "A+")
        )

        val gradeCalculator = GradeCalculator(courses)
        val result: Double = gradeCalculator.calculateGrade()

        assertThat(result).isEqualTo(4.5)
    }
}