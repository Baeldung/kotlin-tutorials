package com.baeldung.lambdamocks

import com.nhaarman.mockito_kotlin.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import kotlin.test.assertEquals

class CalculatorTest {

  class Calculator(private val operation: (Int, Int) -> Int) {
      fun calculate(a: Int, b: Int): Int {
          return operation(a, b)
      }
  }
    @Test
    fun testCalculateWithMockito() {
        val operationMock: (Int, Int) -> Int = mock()
        val calculator = Calculator(operationMock)
        whenever(operationMock.invoke(any(), any())).thenReturn(50)
        val result = calculator.calculate(10, 5)
        assertEquals(50, result)
        verify(operationMock).invoke(eq(10), eq(5))
    }


    @Test fun testCalculate() {
        val operation: (Int, Int) -> Int = { a, b -> a - b }
        val calculator = Calculator(operation)
        val result = calculator.calculate(10, 5)
        assertEquals(5, result)
    }

    @Test
    fun testCalculateWithMockk() {
        val operationMock = mockk<(Int, Int) -> Int>()
        every { operationMock(any(), any()) } returns 50
        val calculator = Calculator(operationMock)
        val result = calculator.calculate(10, 5)
        assertEquals(50, result)
        verify { operationMock(10, 5) } }
}


