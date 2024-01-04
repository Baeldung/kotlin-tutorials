package com.baeldung.thymeleaf.server

import com.baeldung.thymeleaf.server.plugins.configureRouting
import com.baeldung.thymeleaf.server.plugins.configureStatusPages
import com.baeldung.thymeleaf.server.plugins.configureTemplating
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureTemplating()
        configureRouting()
        configureStatusPages()
    }.start(wait = true)
}
