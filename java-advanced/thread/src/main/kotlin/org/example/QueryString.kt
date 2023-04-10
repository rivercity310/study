package org.example

data class QueryString(private val key: String, private val value: String) {
    internal fun getValue(): String =
        this.value

    internal fun exists(key: String): Boolean =
        this.key == key
}
