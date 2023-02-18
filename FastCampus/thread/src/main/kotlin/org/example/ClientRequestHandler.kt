package org.example

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.IllegalArgumentException
import java.net.Socket
import java.nio.charset.StandardCharsets

class ClientRequestHandler(private val clientSocket: Socket): Runnable {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ClientRequestHandler::class.java)
    }

    override fun run() {

        logger.info("[ClientRequestHandler] new client {} started.", Thread.currentThread().name)

        try {
            val inputStream = clientSocket.getInputStream()
            val outputStream = clientSocket.getOutputStream()

            val br = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            val dos = DataOutputStream(outputStream)

            val httpRequest = HttpRequest(br)

            if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                val queryStrings = httpRequest.getQueryStrings()

                val op1 = queryStrings.getValue("operand1").toInt()
                val operator = queryStrings.getValue("operator")
                val op2 = queryStrings.getValue("operand2").toInt()

                val result = when (operator) {
                    "+" -> op1 + op2
                    "-" -> op1 - op2
                    "*" -> op1 * op2
                    "/" -> {
                        if (op2 == 0)
                            throw IllegalArgumentException("0으로 나눌 수 없음")
                        else
                            op1 / op2
                    }
                    else -> throw IllegalArgumentException("잘못된 오퍼레이터")
                }

                val body = result.toString().toByteArray()
                val httpResponse = HttpResponse(dos)

                httpResponse.response200Header("application/json", body.size)
                httpResponse.responseBody(body)
            }

        } catch (e: Exception) { e.stackTrace }

    }
}