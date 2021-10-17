package com.baeldung.logging

import mu.KotlinLogging
import mu.withLoggingContext

private val logger = KotlinLogging.logger {}

fun main() {
    withLoggingContext("user" to "Baeldung") {
        logger.info { "Log with MDC" }
    }
}