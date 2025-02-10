package com.baeldung.log4j

import org.apache.logging.log4j.kotlin.logger

class Enemy {
    fun shoot() {
        logger.warn { "Enemy shoots the player" }
    }
}