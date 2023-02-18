package org.example

import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.IllegalArgumentException
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.StandardCharsets
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CustomWebApplicationServer(private val port: Int) {
    companion object {
        private val logger = LoggerFactory.getLogger(CustomWebApplicationServer::class.java)
    }

    // Thread Pool
    private val executorService: ExecutorService = Executors.newFixedThreadPool(10)

    internal fun start() {
        val serverSocket = ServerSocket(port)
        logger.info("[CustomWebApplicationServer] started $port port")

        var clientSocket: Socket
        logger.info("[CustomWebApplicationServer] waiting for client")

        while (true) {
            clientSocket = serverSocket.accept()
            logger.info("[CustomWebApplicationServer] client connected")

            // Thread(ClientRequestHandler(clientSocket)).start()
            executorService.execute(ClientRequestHandler(clientSocket))
        }
    }
}