package com.baeldung.logging

import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.oshai.kotlinlogging.withLoggingContext

private val logger = KotlinLogging.logger {}

fun main() {
    withLoggingContext("user" to "Baeldung") {
        logger.info { "Log with MDC" }
    }
}
