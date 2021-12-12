package com.baeldung.execjar

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    routing {
        val jokeRepository = JokeRepository("/list-of-jokes.txt")
        get("/") {
            call.respondText(jokeRepository.provideJoke())
        }
    }
}