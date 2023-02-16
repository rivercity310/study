package org.example

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class RequestLineTest {
    @Test
    fun create() {
        val requestLine = RequestLine(requestLine = "GET http://localhost:8080/calculate?operand=11&operator=+&operand2=22 HTTP/1.1")
        assertThat(requestLine).isNotNull
        assertThat(requestLine).isEqualTo(RequestLine(
            method = "GET",
            urlPath = "http://localhost:8080/calculate",
            queryString = "operand=11&operator=+&operand2=22"
        ))
    }
}