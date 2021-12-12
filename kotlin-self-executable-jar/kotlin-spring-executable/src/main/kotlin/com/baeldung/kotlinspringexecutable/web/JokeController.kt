package com.baeldung.kotlinspringexecutable.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter
import kotlin.random.Random
import kotlin.random.nextInt

class JokeController(resource: String) {
    private val jokes = javaClass.getResourceAsStream(resource)!!.bufferedReader().readLines()
    private val random = Random(System.currentTimeMillis())

    suspend fun handleGet(request: ServerRequest): ServerResponse =
        random.nextInt(range = jokes.indices)
            .let { ok().bodyValueAndAwait(jokes[it]) }
}

@Configuration
class RouterConfiguration {
    @Bean
    fun controller() = JokeController("/list-of-jokes.txt")

    @Bean
    fun mainRoute(controller: JokeController): RouterFunction<ServerResponse> = coRouter {
        GET("/", controller::handleGet)
    }
}