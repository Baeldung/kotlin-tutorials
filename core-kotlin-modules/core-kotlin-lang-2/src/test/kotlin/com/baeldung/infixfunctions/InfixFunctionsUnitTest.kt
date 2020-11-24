package com.baeldung.infixfunctions

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test

class InfixFunctionsUnitTest {
    @Test
    fun testColours() {
        val color = 0x123456
        val red = (color and 0xff0000) shr 16
        val green = (color and 0x00ff00) shr 8
        val blue = (color and 0x0000ff) shr 0

        assertEquals(0x12, red)
        assertEquals(0x34, green)
        assertEquals(0x56, blue)
    }

    @Test
    fun testNewAssertions() {
        class Assertion<T>(private val target: T) {
            infix fun isEqualTo(other: T) {
                assertEquals(other, target)
            }

            infix fun isDifferentFrom(other: T) {
                assertNotEquals(other, target)
            }
        }

        val result = Assertion(5)

        result isEqualTo 5

        // The following two lines are expected to fail
        // result isEqualTo 6
        // result isDifferentFrom 5
    }

    @Test
    fun testNewStringMethod() {
        infix fun String.substringMatches(r: Regex) : List<String> {
            return r.findAll(this)
                    .map { it.value }
                    .toList()
        }

        val matches = "a bc def" substringMatches ".*? ".toRegex()
        assertEquals(listOf("a ", "bc "), matches)
    }
}
