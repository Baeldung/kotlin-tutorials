package com.baeldung.optimizeWhenStatement

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OptimizeReturnStatmentInWhenExpressionUnitTest {
}

fun getErrorCode(type: String): String {
    when (type) {
        "error" -> return "404"
        "success" -> return "200"
        else -> return "Unknown type"
    }
}

fun getErrorCodeOptimized(type: String): String {
    val result = when (type) {
        "error" -> "404"
        "success" -> "200"
        else -> "Unknown type"
    }
    println("Error status: $result")

    return result
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
     when {
        number < 0 -> {
            println("Logging negative number: $number")
            return "Negative"
        }
        number % 2 == 0 -> {
            println("Logging even number: $number")
            return "Even"
        }
        else -> {
            println("Logging odd number: $number")
            return "Odd"
        }
    }
}

fun categorizeNumberOptimized(number: Int): String {
    return when {
        number < 0 -> {
            println("Logging negative number: $number")
            "Negative"
        }
        number % 2 == 0 -> {
            println("Logging even number: $number")
            "Even"
        }
        else -> {
            println("Logging odd number: $number")
            "Odd"
        }
    }
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
    println("Processing status: $result")

    return result
}