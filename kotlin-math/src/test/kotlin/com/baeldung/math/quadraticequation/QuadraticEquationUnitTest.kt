package com.baeldung.math.quadraticequation

import org.apache.commons.lang3.tuple.Pair
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.apache.commons.math3.complex.Complex

class QuadraticEquationUnitTest {

    @Test
    fun `test solveForRealRoots for equation with real roots should give correct results`() {
        val solver = QuadraticEquationSolver(1.0, 2.0, 1.0)
        val expectedRoots = Pair(-1.0, -1.0)
        val actualRoots = solver.solveForRealRoots()
        Assertions.assertEquals(expectedRoots, actualRoots)
    }

    @Test
    fun `test solveForComplexRoots for equation with complex real roots should give correct results`() {
        val solver = QuadraticEquationSolver(1.0, 2.0, 1.0)
        val expectedRoots = Pair(Complex(-1.0), Complex(-1.0))
        val actualRoots = solver.solveForComplexRoots()
        Assertions.assertEquals(expectedRoots, actualRoots)
    }

    @Test
    fun `test solveForComplexRoots for equation with complex imaginary roots should give correct results`() {
        val solver = QuadraticEquationSolver(1.0, 1.0, 1.0)
        val expectedRoots = Pair(Complex(-0.5, Math.sqrt(3.0) * 0.5), Complex(-0.5, -1 * Math.sqrt(3.0) * 0.5))
        val actualRoots = solver.solveForComplexRoots()
        Assertions.assertEquals(expectedRoots, actualRoots)
    }
}