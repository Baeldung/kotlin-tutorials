package com.baeldung.logging

import io.github.oshai.kotlinlogging.KLoggable
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KLogging
import io.github.oshai.kotlinlogging.KotlinLogging

private val staticLogger = KotlinLogging.logger {}

class ClassForImportantWork1 {
    companion object : KLogging()

    fun importantWork() {
        logger.debug { "I'm logging via companion object" }
    }
}

class ClassForImportantWork2 : KLoggable {
    override val logger: KLogger = logger()

    fun importantWork() {
        logger.debug { "I'm logging via non static member" }
    }
}
