package com.baeldung.booleanValueOfKotlinEquivalent

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class BooleanValueOfKotlinEquivalentUnitTest {

    @Test
    fun `obtain equivalent in Kotlin by string comparison using equals method`() {
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
        var str: String? = "fjdfj"
        assertEquals(true, "true".toBooleanStrictOrNull())
        assertEquals(null, str?.toBooleanStrictOrNull())
        assertEquals(false, "false".toBooleanStrictOrNull())
    }

    @Test
    fun `obtain equivalent in Kotlin using toBooleanStrict method`() {
        assertEquals(true, "true".toBooleanStrict())
        assertEquals(false, "false".toBooleanStrict())
        assertThrows(IllegalArgumentException::class.java) {
            "TRUE".toBooleanStrict()
        }
    }

    @Test
    fun `obtain equivalent in Kotlin using parseBoolean method`() {
        assertEquals(true, java.lang.Boolean.parseBoolean("true"))
        assertEquals(true, java.lang.Boolean.parseBoolean("TRUE"))
        assertEquals(false, java.lang.Boolean.parseBoolean("false"))
    }

    @Test
    fun `obtain equivalent in Kotlin using valueOf method`() {
        assertEquals(true, java.lang.Boolean.valueOf("true"))
        assertEquals(true, java.lang.Boolean.valueOf("TRUE"))
        assertEquals(false, java.lang.Boolean.valueOf("false"))
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
    return input.trim().matches("^\\s*(?i:true)\\s*$".toRegex())
}

fun String.toBooleanValue(): Boolean =
    when (this.toLowerCase()) {
        "true" -> true
        else -> false
    }