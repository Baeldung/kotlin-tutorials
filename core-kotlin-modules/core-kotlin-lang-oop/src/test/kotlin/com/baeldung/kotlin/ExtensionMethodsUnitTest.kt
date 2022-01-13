package com.baeldung.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtensionMethodsUnitTest {
    @Test
    fun simpleExtensionMethod() {
        assertEquals("Nothing", "Nothing".escapeForXml())
        assertEquals("&lt;Tag&gt;", "<Tag>".escapeForXml())
        assertEquals("a&amp;b", "a&b".escapeForXml())
    }

    @Test
    fun genericExtensionMethod() {
        fun <T> T.concatAsString(b: T) : String {
            return this.toString() + b.toString()
        }

        assertEquals("12", "1".concatAsString("2"))
        assertEquals("12", 1.concatAsString(2))
        // This doesn't compile
        // assertEquals("12", 1.concatAsString(2.0))
    }

    @Test
    fun infixExtensionMethod() {
        infix fun Number.toPowerOf(exponent: Number): Double {
            return Math.pow(this.toDouble(), exponent.toDouble())
        }

        assertEquals(9.0, 3 toPowerOf 2, 0.1)
        assertEquals(3.0, 9 toPowerOf 0.5, 0.1)
    }

    @Test
    fun operatorExtensionMethod() {
        operator fun List<Int>.times(by: Int): List<Int> {
            return this.map { it * by }
        }

        assertEquals(listOf(2, 4, 6), listOf(1, 2, 3) * 2)
    }
}
