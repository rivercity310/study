package org.example

import org.assertj.core.api.Assertions
import org.example.domain.Cook
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CookTest {
    @DisplayName("요리 생성")
    @Test
    fun createTest() {
        Assertions.assertThatCode { Cook("만두", 5000) }
            .doesNotThrowAnyException()
    }
}