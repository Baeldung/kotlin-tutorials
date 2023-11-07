package com.baeldung.consoleapp

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(0)
class CommandLineFirstComponent: CommandLineRunner {

    private val log = LoggerFactory.getLogger(CommandLineFirstComponent::class.java)

    override fun run(vararg args: String?) {
        log.info(CommandLineFirstComponent::class.simpleName)
    }
}