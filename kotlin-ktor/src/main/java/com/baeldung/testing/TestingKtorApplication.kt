package com.baeldung.testing

import com.baeldung.testing.plugins.configureContentNegotiation
import com.baeldung.testing.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureContentNegotiation()
    }.start(wait = true)
}
