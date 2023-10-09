package com.baeldung.graphql

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    embeddedServer(CIO, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
        }
    }.start(wait = true)}