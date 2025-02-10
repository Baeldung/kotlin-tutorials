package com.baeldung.log4j

import org.apache.logging.log4j.kotlin.Logging

class GameUtil : Logging {
    fun logEvent(event: String) {
        logger.info { "Game Event: $event" }
    }
}