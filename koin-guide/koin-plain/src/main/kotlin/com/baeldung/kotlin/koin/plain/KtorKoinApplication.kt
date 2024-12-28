package com.baeldung.kotlin.koin.plain

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.ktor.ext.inject
import org.koin.ktor.ext.koin

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    koin {
        modules(koinModule)
    }
    routing {
        val helloSayer: HelloSayer by inject()
        get("/") {
            call.respondText("${helloSayer.sayHello()}, world!")
        }
    }
}