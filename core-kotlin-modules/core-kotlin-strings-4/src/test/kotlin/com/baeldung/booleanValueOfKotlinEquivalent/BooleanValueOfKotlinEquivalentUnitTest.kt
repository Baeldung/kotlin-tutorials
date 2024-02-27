package com.baeldung.booleanValueOfKotlinEquivalent

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BooleanValueOfKotlinEquivalentUnitTest {

    @Test
    fun `obtain equivalent in Kotlin using if statement`() {
        assertEquals(true, convertStringToBoolean("true"))
        assertEquals(true, convertStringToBoolean("TRUE"))
        assertEquals(false, convertStringToBoolean("false"))
    }

    @Test
    fun `obtain equivalent in Kotlin using toBoolean method`() {
        assertEquals(true, "true".toBoolean())
        assertEquals(true, "TRUE".toBoolean())
        assertEquals(false, "false".toBoolean())
    }

    @Test
    fun `obtain equivalent in Kotlin using toBooleanStrictOrNull method`() {
        assertEquals(true, "true".toBooleanStrictOrNull())
        assertEquals(null, "TRUE".toBooleanStrictOrNull())
        assertEquals(false, "false".toBooleanStrictOrNull())
    }

    @Test
    fun `obtain equivalent in Kotlin using parseBoolean method`() {
        assertEquals(true, java.lang.Boolean.parseBoolean("true"))
        assertEquals(true, java.lang.Boolean.parseBoolean("TRUE"))
        assertEquals(false, java.lang.Boolean.parseBoolean("false"))
    }

    @Test
    fun `obtain equivalent in Kotlin using toBooleanValue method`() {
        assertEquals(true, "true".toBooleanValue())
        assertEquals(true, "TRUE".toBooleanValue())
        assertEquals(false, "false".toBooleanValue())
        assertEquals(null, "trueorfalse".toBooleanValue())
    }

    @Test
    fun `obtain equivalent in Kotlin using regex`() {
        assertEquals(true, convertStringToBooleanUsingRegex("true"))
        assertEquals(true, convertStringToBooleanUsingRegex("TRUE"))
        assertEquals(false, convertStringToBooleanUsingRegex("false"))
    }

}
fun convertStringToBoolean(input: String): Boolean {
    return if (input.equals("true", ignoreCase = true)) true else false
}

fun convertStringToBooleanUsingRegex(input: String): Boolean {
    return input.matches("^\\s*(?i:true)\\s*$".toRegex())
}

fun String.toBooleanValue(): Boolean? =
    when (this.toLowerCase()) {
        "true" -> true
        "false" -> false
        else -> null
    }