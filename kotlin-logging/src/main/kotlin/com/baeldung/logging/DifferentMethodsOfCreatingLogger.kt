package com.baeldung.logging

import mu.KLoggable
import mu.KLogger
import mu.KLogging
import mu.KotlinLogging

private val staticLogger = KotlinLogging.logger {}

class ClassForImportantWork1 {
    companion object: KLogging()

    fun importantWork() {
        logger.debug { "I'm logging via companion object" }
    }
}

class ClassForImportantWork2: KLoggable {
    override val logger: KLogger = logger()

    fun importantWork() {
        logger.debug { "I'm logging via non static member" }
    }
}
