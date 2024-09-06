package com.baeldung.functional_dsl.router_function

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.RouterFunctions
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

@SpringBootApplication
open class Application {

    @Bean
    open fun configure(): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .GET("/endpoint/{country}") {
                ServerResponse.ok().body(
                    mapOf(
                        "name" to it.param("name"),
                        "age" to it.headers().header("X-age")[0],
                        "country" to it.pathVariable("country")
                    )
                )
            }
            .build()
    }
}

fun main(vararg args: String) {
    runApplication<Application>(*args)
}