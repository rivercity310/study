package org.example

import java.io.BufferedReader

class HttpRequest(br: BufferedReader) {
    private val requestLine: RequestLine = RequestLine(br.readLine())

    internal fun isGetRequest(): Boolean =
        requestLine.isGetRequest()

    internal fun matchPath(path: String): Boolean =
        requestLine.matchPath(path)

    internal fun getQueryStrings() =
        requestLine.getQueryStrings()
}