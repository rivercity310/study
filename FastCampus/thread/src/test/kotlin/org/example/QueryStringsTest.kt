package org.example

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class QueryStringsTest {
    @Test
    fun createTest() {
        // 일급 컬렉션 QueryStrings
        val queryStrings = QueryStrings("operand=1&operator=*&operand2=22")     // List<QueryString>
        assertThat(queryStrings).isNotNull
    }
}