package com.baeldung.mockito.argumentcaptor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

interface MathOperations {
    fun performOperation(operation: (a: Int, b: Int) -> Int): Int
}

class CalculatorService(private val mathOperations: MathOperations) {
    fun calculate(operation: (a: Int, b: Int) -> Int): Int {
        return mathOperations.performOperation(operation)
    }
}

class CalculatorServiceUnitTest {

    @Test
    fun `performs addition operation`() {
        // given
        val mathOperations = mock<MathOperations>()
        val calculatorService = CalculatorService(mathOperations)

        // when
        calculatorService.calculate { a, b -> a + b }

        // then
        val lambdaCaptor = argumentCaptor<(Int, Int) -> Int>()

        verify(mathOperations).performOperation(lambdaCaptor.capture())

        val capturedOperation = lambdaCaptor.firstValue
        val operationResult = capturedOperation(11, 3)

        assertEquals(14, operationResult)
    }

    @Test
    fun `performs subtraction operation`() {
        // given
        val mathOperations = mock<MathOperations>()
        val calculatorService = CalculatorService(mathOperations)

        // when
        calculatorService.calculate { a, b -> a - b }

        // then
        val lambdaCaptor = argumentCaptor<(Int, Int) -> Int>()

        verify(mathOperations).performOperation(lambdaCaptor.capture())

        val capturedOperation = lambdaCaptor.firstValue
        val operationResult = capturedOperation(11, 3)

        assertEquals(8, operationResult)
    }

    @Test
    fun `performs multiplication operation`() {
        // given
        val mathOperations = mock<MathOperations>()
        val calculatorService = CalculatorService(mathOperations)

        // when
        calculatorService.calculate { a, b -> a * b }

        // then
        val lambdaCaptor = argumentCaptor<(Int, Int) -> Int>()

        verify(mathOperations).performOperation(lambdaCaptor.capture())

        val capturedOperation = lambdaCaptor.firstValue
        val operationResult = capturedOperation(11, 3)

        assertEquals(33, operationResult)
    }
}