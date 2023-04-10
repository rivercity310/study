package org.example

import org.apache.catalina.startup.Tomcat
import org.slf4j.LoggerFactory
import java.io.File

class WebApplicationServer

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger(WebApplicationServer::class.java)
    val webappDirLocation = "webapps/"
    val tomcat = Tomcat()

    tomcat.setPort(8080)
    tomcat.addWebapp("/", File(webappDirLocation).absolutePath)

    log.debug("[WebApplicationServer] tomcat started on port 8080")

    tomcat.start()
    tomcat.server.await()
}