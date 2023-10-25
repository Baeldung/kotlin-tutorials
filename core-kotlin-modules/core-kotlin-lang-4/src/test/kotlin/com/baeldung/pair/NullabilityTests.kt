package com.baeldung.pair

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class NullabilityTests {
    @Test
    fun testLengthIsNullWhenNameIsNull() {
        val name: String? = null
        val length = name?.length
        assertEquals(null, length)
    }

    @Test
    fun testNullPointerException() {
        val name: String? = null
        assertFailsWith<NullPointerException> {
            val length = name!!.length
        }
    }
}
