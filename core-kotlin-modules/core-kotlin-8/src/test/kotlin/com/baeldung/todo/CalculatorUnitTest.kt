package com.baeldung.todo

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CalculatorUnitTest {

    @Test
    fun `calculator should add two numbers`() {
        val calculator = Calculator()

        assertThrows<NotImplementedError>("Implement a + b") {
            calculator.add(1, 2)
        }
    }
}