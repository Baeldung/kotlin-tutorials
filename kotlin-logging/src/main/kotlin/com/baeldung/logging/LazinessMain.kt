package com.baeldung.logging

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {
    val bigEvaluationFunction: (String) -> String = {
        println("I am a VERY BIG evaluation: $it")
        "Big Evaluation"
    }

    logger.trace("Running big evaluation: ${bigEvaluationFunction("eagerly")}")

    logger.trace { "Running big evaluation: ${bigEvaluationFunction("lazily")}" }
}