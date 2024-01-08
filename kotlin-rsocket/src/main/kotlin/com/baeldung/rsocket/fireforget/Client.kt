package com.baeldung.rsocket.fireforget

import io.ktor.client.*
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.ktor.client.rSocket
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val client = HttpClient {
            install(io.ktor.client.plugins.websocket.WebSockets)
            install(io.rsocket.kotlin.ktor.client.RSocketSupport)
        }

        val rSocket: RSocket = client.rSocket(host = "localhost", port = 9000, path = "/rsocket/fireAndForget")
        rSocket.fireAndForget(buildPayload { data("Hello") })
    }
}