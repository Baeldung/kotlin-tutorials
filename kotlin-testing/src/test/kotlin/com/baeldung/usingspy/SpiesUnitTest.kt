package com.baeldung.usingspy

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test

class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }
    fun findAverage(a: Int, b: Int): Int {
        val total = add(a, b)
        return total / 2;
    }
}

class CalculatorTest {
    @Test
    fun testSpy() {
        val spy = spyk<Calculator>()
        val result = spy.findAverage(5, 5)
        assertEquals(5, result)
    }

    @Test
    fun testPartialMocking() {
        val spy = spyk<Calculator>()
        every { spy.add(any(), any()) } returns 2
        val result = spy.findAverage(5, 5)
        verify { spy.add(5, 5) }
        assertEquals(1, result)
        clearMocks(spy)
    }
}
