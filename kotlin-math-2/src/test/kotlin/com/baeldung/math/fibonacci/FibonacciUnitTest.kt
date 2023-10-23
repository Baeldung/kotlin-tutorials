package com.baeldung.math.fibonacci

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class FibonacciUnitTest {

    @Test
    fun `test checkIfFibonacciUsingRecursion return correct value`() {
        Assertions.assertThat(fibonacciUsingRecursion(8)).isEqualTo(21)
    }

    @Test
    fun `test checkIfFibonacciUsingIteration return correct value`() {
        Assertions.assertThat(fibonacciUsingIteration(8)).isEqualTo(21)
    }

    @Test
    fun `test checkIfFibonacciUsingTailRecursion return correct value`() {
        Assertions.assertThat(fibonacciUsingTailRecursion(8)).isEqualTo(21)
    }

    @Test
    fun `test checkIfFibonacciSequence return correct value`() {
        Assertions.assertThat(fibonacciUsingSequence(8)).isEqualTo(21)
    }

}
