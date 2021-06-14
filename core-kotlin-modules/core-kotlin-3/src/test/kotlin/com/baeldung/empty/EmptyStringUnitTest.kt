package com.baeldung.empty

import org.junit.jupiter.api.Test
import java.lang.StringBuilder
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EmptyStringUnitTest {

    @Test
    fun `isEmpty returns true for non-null empty strings`() {
        val empty = ""
        val nonEmpty = "42"
        val sb = StringBuilder()

        assertTrue { empty.isEmpty() }
        assertTrue { nonEmpty.isNotEmpty() }
        assertTrue { sb.isEmpty() }
    }

    @Test
    fun `isNullOrEmpty returns true if the string is either null or empty`() {
        val nullStr: String? = null
        val emptyNullable: String? = ""

        assertTrue { nullStr.isNullOrEmpty() }
        assertTrue { emptyNullable.isNullOrEmpty() }
    }

    @Test
    fun `orEmpty should provide default values for empty strings`() {
        val ip = ""

        assertEquals("default-value", ip.ifEmpty { "default-value" })
    }

    @Test
    fun `isBlank returns true for non-null blank strings`() {
        val blank = "    "
        val empty = ""
        val notBlank = "  42"

        assertTrue { empty.isBlank() }
        assertTrue { blank.isBlank() }
        assertTrue { notBlank.isNotBlank() }
    }

    @Test
    fun `isNullOrBlank returns true if the string is either null or blank`() {
        val nullStr: String? = null
        val blankNullable: String? = "   "

        assertTrue { nullStr.isNullOrBlank() }
        assertTrue { blankNullable.isNullOrBlank() }
    }
}
