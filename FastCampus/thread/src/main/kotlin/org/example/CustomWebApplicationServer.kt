package org.example

import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.IllegalArgumentException
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.StandardCharsets

class CustomWebApplicationServer(private val port: Int) {
    private val logger = LoggerFactory.getLogger(CustomWebApplicationServer::class.java)

    internal fun start() {
        val serverSocket = ServerSocket(port)
        logger.info("[CustomWebApplicationServer] started $port port")

        var clientSocket: Socket
        logger.info("[CustomWebApplicationServer] waiting for client")

        while (true) {
            clientSocket = serverSocket.accept()
            logger.info("[CustomWebApplicationServer] client connected")

            // 사용자 요청을 메인 스레드에서 처리한다
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
}