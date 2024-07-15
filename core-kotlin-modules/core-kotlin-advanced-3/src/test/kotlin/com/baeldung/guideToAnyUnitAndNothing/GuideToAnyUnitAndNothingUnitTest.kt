package com.baeldung.guideToAnyUnitAndNothing

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GuideToAnyUnitAndNothingUnitTest {

    @Test
    fun `prints value for any type`() {
        val intValue = 14
        val stringValue = "Hello Kotlin"

       assertEquals("Hello Kotlin", printAnyType(stringValue))
       assertEquals(14, printAnyType(intValue))
    }

    @Test
    fun `shows how to use unit type`() {
        val result = performTask()
        assertNotNull(result)
    }

    @Test
    fun `shows how to use nothing type`() {
        assertFailsWith<IllegalArgumentException> {
            throwError("Invalid argument")
        }
    }
}
fun printAnyType(value: Any): Any {
    return value
}
fun performTask(): Unit {
    println()
}
fun throwError(message: String): Nothing {
    throw IllegalArgumentException(message)
}