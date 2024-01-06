package com.baeldung.client

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::websocketsModule).start(wait = true)
}

fun Application.websocketsModule() {
    install(WebSockets)
    routing {
        webSocket("/messages") {
            send("Hi!")
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                val receivedText = frame.readText()
                if (receivedText.equals("Bye!", ignoreCase = true)) {
                    close(CloseReason(CloseReason.Codes.NORMAL, "Client said bye!"))
                }
            }
        }
        webSocket("/driver") {
            send("Which driver would you like to see?")
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                val receivedText = frame.readText()
                if (receivedText.equals("Bye!", ignoreCase = true)) {
                    close(CloseReason(CloseReason.Codes.NORMAL, "Client said bye!"))
                }
                val receivedDriverId = receivedText.toIntOrNull() ?: continue
                send(DRIVERS[receivedDriverId])
            }
        }
    }
}