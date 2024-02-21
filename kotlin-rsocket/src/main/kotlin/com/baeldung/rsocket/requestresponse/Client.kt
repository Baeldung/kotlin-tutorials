package com.baeldung.rsocket.requestresponse

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

        val rSocket: RSocket = client.rSocket(port = 9000, path = "/rsocket/requestResponse")
        val response = rSocket.requestResponse(buildPayload { data("Hello") })

        val text = response.data.readText()
        println("Received response: $text")
    }
}