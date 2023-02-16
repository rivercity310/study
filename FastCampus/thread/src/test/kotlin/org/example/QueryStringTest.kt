package org.example

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class QueryStringTest {
    @Test
    fun createTest() {
        val queryString = QueryString("operand", "11")

        assertThat(queryString).isNotNull
    }
}