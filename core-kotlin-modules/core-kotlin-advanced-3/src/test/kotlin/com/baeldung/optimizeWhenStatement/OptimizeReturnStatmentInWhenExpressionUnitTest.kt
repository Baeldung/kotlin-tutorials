package com.baeldung.optimizeWhenStatement

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OptimizeReturnStatmentInWhenExpressionUnitTest {

    @Test
    fun `obtain correct error code from optimized when method`() {
        assertEquals("404", getErroCodeOptimized("error"))
        assertEquals("200", getErroCodeOptimized("success"))
        assertEquals("Unknown type", getErroCodeOptimized("unknown"))
    }

    @Test
    fun `obtain correct error code from optimized when method using single expression approach`() {
        assertEquals("404", getErrorCodeOptimizedUsingExpressionBodySyntax("error"))
        assertEquals("200", getErrorCodeOptimizedUsingExpressionBodySyntax("success"))
        assertEquals("Unknown type", getErrorCodeOptimizedUsingExpressionBodySyntax("unknown"))
    }

    @Test
    fun `categorize number from optimized complex when method using refactoring approach`() {
        assertEquals("Negative", categorizeNumber(-5))
        assertEquals("Even", categorizeNumber(4))
        assertEquals("Odd", categorizeNumber(7))
    }

    @Test
    fun `process status from simplified when expression via variable aassignment`() {
        assertEquals("Processing", processStatusOptimized("active"))
        assertEquals("Idle", processStatusOptimized("inactive"))
        assertEquals("Error occurred", processStatusOptimized("error"))
        assertEquals("Unknown status", processStatusOptimized("unknown"))
    }
}

fun getErroCode(type: String): String {
    when (type) {
        "error" -> return "404"
        "success" -> return "200"
        else -> return "Unknown type"
    }
}

fun getErroCodeOptimized(type: String): String {
    return when (type) {
        "error" -> "404"
        "success" -> "200"
        else -> "Unknown type"
    }
}

fun getErrorCodeOptimizedUsingExpressionBodySyntax(type: String): String = when (type) {
    "error" -> "404"
    "success" -> "200"
    else -> "Unknown type"
}

fun getMessageLength(type: String): Int {
    when (type) {
        "short" -> return 5
        "medium" -> return 10
        "long" -> return 20
        else -> return 0
    }
}

fun categorizeNumber(number: Int): String {
    return when {
        number < 0 -> {
            logNegativeNumber(number)
            return "Negative"
        }
        number % 2 == 0 -> {
            logEvenNumber(number)
            return "Even"
        }
        else -> {
            logOddNumber(number)
            return "Odd"
        }
    }
}

fun categorizeNumberOptimized(number: Int): String {
    return when {
        number < 0 -> {
            logNegativeNumber(number)
            "Negative"
        }
        number % 2 == 0 -> {
            logEvenNumber(number)
            "Even"
        }
        else -> {
            logOddNumber(number)
            "Odd"
        }
    }
}

fun logNegativeNumber(number: Int) {
    println("Logging negative number: $number")
}

fun logEvenNumber(number: Int) {
    println("Logging even number: $number")
}

fun logOddNumber(number: Int) {
    println("Logging odd number: $number")
}

fun processStatus(status: String): String {
     when (status) {
        "active" -> return "Processing"
        "inactive" -> return "Idle"
        "error" -> return "Error occurred"
        else -> return "Unknown status"
    }
}

fun processStatusOptimized(status: String): String {
    val result = when (status) {
        "active" -> "Processing"
        "inactive" -> "Idle"
        "error" -> "Error occurred"
        else -> "Unknown status"
    }
    return result
}