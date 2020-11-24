package com.baeldung.range

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FirstLastUnitTest {

    @Test
    fun testFirst() {
        assertEquals(1, (1..9).first)
    }

    @Test
    fun testLast() {
        assertEquals(9, (1..9).last)
    }

    @Test
    fun testStep() {
        assertEquals(2, (1..9 step 2).step)
    }
}