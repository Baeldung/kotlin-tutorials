package com.baeldung.rsocket.fireforget

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.rsocket.kotlin.RSocketRequestHandler
import io.rsocket.kotlin.ktor.server.rSocket
import io.rsocket.kotlin.payload.Payload

fun main() {
    embeddedServer(Netty, port = 9000) {
        install(io.ktor.server.websocket.WebSockets)
        install(io.rsocket.kotlin.ktor.server.RSocketSupport)
        routing {
            // Add RSocket endpoints

            rSocket("/rsocket/fireAndForget") {
                RSocketRequestHandler {
                    fireAndForget { request: Payload ->
                        val text = request.data.readText()
                        println("Received request: $text")
                    }
                }
            }
        }
    }.start(wait = true)
}