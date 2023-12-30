package com.baeldung.rsocket.requestresponse

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.rsocket.kotlin.RSocketRequestHandler
import io.rsocket.kotlin.ktor.server.rSocket
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.time.delay
import java.time.Duration

fun main() {
    embeddedServer(Netty, port = 9000) {
        install(io.ktor.server.websocket.WebSockets)
        install(io.rsocket.kotlin.ktor.server.RSocketSupport)
        routing {
            // Add RSocket endpoints

            rSocket("/rsocket/requestResponse") {
                RSocketRequestHandler {
                    requestResponse { request: Payload ->
                        val text = request.data.readText()
                        println("Received request: $text")

                        delay(Duration.ofSeconds(5))

                        buildPayload { data(text.reversed()) }
                    }
                }
            }
        }
    }.start(wait = true)
}