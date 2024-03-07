package com.baeldung.nullability

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NullabilityUnitTest {

    @Test
    fun `Should return ten`() {
        val value: Int? = 10
        assertEquals(10, value!!)
    }

    @Test
    fun `Should throw NullPointerException`() {
        val value: Int? = null
        assertFailsWith<NullPointerException>(
            block = { value!! }
        )
    }

    @Test
    fun `Should return the default value`() {
        val value: Int? = null
        assertEquals(0, value ?: 0)
    }

    @Test
    fun `Should return ignore the default value`() {
        val value: Int? = 10
        assertEquals(10, value ?: 0)
    }

}