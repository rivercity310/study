package org.example

import java.lang.IllegalArgumentException

class QueryStrings(queryStringLine: String) {
    private val queryStrings: List<QueryString>

    init {
        this.queryStrings = queryStringLine.split("&")
            .map {
                val pairs = it.split("=")

                if (pairs.size != 2)
                    throw IllegalArgumentException("잘못된 쿼리스트링")

                val key = pairs[0]
                val value = pairs[1]

                QueryString(key, value)
            }
    }

    internal fun getValue(key: String): String =
        this.queryStrings
            .filter { it.exists(key) }
            .map { it.getValue() }[0]

    override fun toString(): String = "$queryStrings"
}