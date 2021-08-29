package com.baeldung.logfilter

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    @Bean
    fun getLogger(): Logger {
        return LoggerFactory.getLogger("log")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
