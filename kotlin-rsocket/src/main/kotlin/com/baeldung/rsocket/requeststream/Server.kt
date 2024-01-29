package com.baeldung.rsocket.requeststream

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.rsocket.kotlin.RSocketRequestHandler
import io.rsocket.kotlin.emitOrClose
import io.rsocket.kotlin.ktor.server.rSocket
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.time.delay
import java.time.Duration

fun main() {
    embeddedServer(Netty, port = 9000) {
        install(io.ktor.server.websocket.WebSockets)
        install(io.rsocket.kotlin.ktor.server.RSocketSupport)
        routing {
            // Add RSocket endpoints

            rSocket("/rsocket/requestStream") {
                RSocketRequestHandler {
                    requestStream { request: Payload ->
                        val text = request.data.readText()
                        println("Received request: $text")

                        flow {
                            processData(text)
                        }
                    }
                }
            }
        }
    }.start(wait = true)
}

suspend fun FlowCollector<Payload>.processData(text: String) {
    for (i in 0..10) {
        val data = "data: ($text)$i"

        println("Emitting $data")
        emitOrClose(buildPayload { data(data) })

        delay(Duration.ofMillis(500))
    }
}