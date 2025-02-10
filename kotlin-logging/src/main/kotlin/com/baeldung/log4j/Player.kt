package com.baeldung.log4j

import org.apache.logging.log4j.kotlin.logger

class Player(val name: String) {
    private val logger = logger("Player Action")

    fun joinGame() {
        logger.info { "Player '$name' has joined the game." }
    }

    fun takeDamage(amount: Int) {
        logger.warn { "Player '$name' took $amount damage!" }
    }
}