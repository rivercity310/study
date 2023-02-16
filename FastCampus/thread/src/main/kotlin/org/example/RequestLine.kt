package org.example

class RequestLine(requestLine: String = "") {
    // "GET http://localhost:8080/calculate?operand=11&operator=+&operand2=22 HTTP/1.1"
    private var method: String = ""
    private var urlPath: String = ""
    private lateinit var queryStrings: QueryStrings

    constructor(method: String, urlPath: String, queryString: String) : this() {
        this.method = method
        this.urlPath = urlPath
        this.queryStrings = QueryStrings(queryString)
    }

    init {
        if (requestLine != "") {
            val tokens = requestLine.split(" ")
            this.method = tokens[0]
            val urlPathTokens = tokens[1].split("?")
            this.urlPath = urlPathTokens[0]
            this.queryStrings = QueryStrings(urlPathTokens[1])
       }

        println("method = $method")
        println("urlPath = $urlPath")
        println("queryString = $queryStrings")
    }

    internal fun isGetRequest(): Boolean =
        this.method == "GET"

    internal fun matchPath(path: String): Boolean =
        this.urlPath == path

    internal fun getQueryStrings(): QueryStrings =
        this.queryStrings

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RequestLine

        if (method != other.method) return false
        if (urlPath != other.urlPath) return false
        if (queryStrings != other.queryStrings) return false

        return true
    }

    override fun hashCode(): Int {
        var result = method.hashCode()
        result = 31 * result + urlPath.hashCode()
        result = 31 * result + queryStrings.hashCode()
        return result
    }
}
