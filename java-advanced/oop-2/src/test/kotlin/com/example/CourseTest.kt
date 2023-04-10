package com.example

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CourseTest {
    @DisplayName("제목(코스) 생성")
    @Test
    fun createTest() {
        assertThatCode { Course("OOP", 3, "A+") }
            .doesNotThrowAnyException()
    }
}