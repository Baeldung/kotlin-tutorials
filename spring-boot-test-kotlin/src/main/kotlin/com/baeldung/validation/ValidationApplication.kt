package com.baeldung.validation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ValidationApplication

fun main(args: Array<String>) {
    runApplication<ValidationApplication>(*args)
}
