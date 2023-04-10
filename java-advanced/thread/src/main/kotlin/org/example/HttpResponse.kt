package org.example

import org.slf4j.LoggerFactory
import java.io.DataOutputStream
import java.io.IOException

class HttpResponse(private val dos: DataOutputStream) {
    private val logger = LoggerFactory.getLogger(HttpResponse::class.java)

    internal fun response200Header(contentType: String, lengthOfBodyContent: Int) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n")
            dos.writeBytes("Content-Type: $contentType;charset=utf-8\r\n")
            dos.writeBytes("Content-Length: $lengthOfBodyContent\r\n")
            dos.writeBytes("\r\n")
        } catch (e: IOException) {
            logger.error(e.message)
        }
    }

    internal fun responseBody(body: ByteArray) {
        try {
            dos.write(body, 0, body.size)
            dos.flush()
        } catch (e: IOException) {
            logger.error(e.message)
        }
    }
}