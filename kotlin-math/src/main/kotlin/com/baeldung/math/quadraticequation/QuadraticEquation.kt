package com.baeldung.math.quadraticequation

import kotlin.math.sqrt

import org.apache.commons.math3.complex.Complex

class QuadraticEquationSolver(val a: Double, val b: Double, val c: Double) {

    fun solveForRealRoots(): Pair<Double, Double>? {
        val discriminant = b * b - 4 * a * c
        if (discriminant < 0) {
            // No real roots
            return null
        }
        val root1 = (-b + sqrt(discriminant)) / (2 * a)
        val root2 = (-b - sqrt(discriminant)) / (2 * a)
        return Pair(root1, root2)
    }

    fun solveForComplexRoots(): Pair<Complex, Complex>? {
        val discriminant = Complex(b * b - 4 * a * c)
        val sqrtDiscriminant = discriminant.sqrt()

        val root1 = (Complex(-b).add(sqrtDiscriminant)).divide(Complex(2 * a))
        val root2 = (Complex(-b).subtract(sqrtDiscriminant)).divide(Complex(2 * a))

        return Pair(root1, root2)
    }
}
