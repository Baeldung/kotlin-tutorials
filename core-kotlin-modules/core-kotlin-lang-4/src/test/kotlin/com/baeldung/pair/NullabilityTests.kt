package com.baeldung.pair

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class NullabilityTests {
    @Test fun testStringLength() {
        val name: String? = "Hello, World!"
        val length = name?.length
        assertEquals(13, length) }

    @Test
    fun testNullPointerException() {
        val name: String? = null
        assertFailsWith<NullPointerException> {
            val length = name!!.length
        }
    }
}
