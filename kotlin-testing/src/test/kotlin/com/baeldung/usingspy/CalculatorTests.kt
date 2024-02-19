package com.baeldung.usingspy

import io.mockk.spyk
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test

class Calculator{
    fun add(a: Int, b: Int): Int {
        return a+b
    }
    fun findAverage(a: Int, b: Int): Int {
        val total = add(a,b)
        return total/2;
    }
}

class CalculatorTest {
    @Test
    fun testSpy() {
        val spy = spyk<Calculator>()
        val result = spy.findAverage(5,5)
        assertEquals(5, result)
    }
}
