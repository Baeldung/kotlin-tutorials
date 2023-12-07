package com.baeldung.facade

import java.util.logging.Logger

class LoggerFacade {
    private val logger = Logger.getLogger("MyApp")

    fun logInfo(message: String) {
        logger.info(message)
    }

    fun logWarning(message: String) {
        logger.warning(message)
    }

    fun logSevere(message: String) {
        logger.severe(message)
    }
}