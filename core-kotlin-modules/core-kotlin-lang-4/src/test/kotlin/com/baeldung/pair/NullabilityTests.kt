package com.baeldung.pair

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import kotlin.test.assertFailsWith

class NullabilityTests {
    @Test
    fun testStringLength() {
        val name: String? = null
        val length = name?.length
        assertNull(length)
    }

    @Test
    fun testNullPointerException() {
        val name: String? = null
        assertFailsWith<NullPointerException> {
            name!!
        }
    }
}
