package com.baeldung.rsocket.requestchannel

import io.ktor.client.*
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.emitOrClose
import io.rsocket.kotlin.ktor.client.rSocket
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.delay
import java.time.Duration

fun main() {
    runBlocking {
        val client = HttpClient {
            install(io.ktor.client.plugins.websocket.WebSockets)
            install(io.rsocket.kotlin.ktor.client.RSocketSupport)
        }

        val rSocket: RSocket = client.rSocket(port = 9000, path = "/rsocket/requestChannel")
        val stream = rSocket.requestChannel(buildPayload { data("Hello") }, flow { produceData() })

        stream.onEach { frame ->
            val text = frame.data.readText()
            println("Received frame: $text")
        }.launchIn(this)
    }
}

suspend fun FlowCollector<Payload>.produceData() {
    for (i in 0..10) {
        val data = "Request: $i"

        println("Emitting $data")
        emitOrClose(buildPayload { data(data) })

        delay(Duration.ofMillis(500))
    }
}