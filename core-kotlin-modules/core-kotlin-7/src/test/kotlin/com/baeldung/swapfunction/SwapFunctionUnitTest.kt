package com.baeldung.swapfunction

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SwapFunctionUnitTest {

    @Test
    fun `when using a temp var, then get the expected result`() {
        var a = 1
        var b = 2
        val temp = a
        a = b
        b = temp
        assertEquals(2, a)
        assertEquals(1, b)
    }

    @Test
    fun `when using the also() function, then get the expected result`() {
        var a = 1
        var b = 2
        a = b.also { b = a }
        assertEquals(2, a)
        assertEquals(1, b)
    }

    @Test
    fun `when using Pair, then get the expected result`() {
        var x = 100
        var y = 200

        (x to y).apply {
            x = second
            y = first
        }
        assertEquals(200, x)
        assertEquals(100, y)
    }
}