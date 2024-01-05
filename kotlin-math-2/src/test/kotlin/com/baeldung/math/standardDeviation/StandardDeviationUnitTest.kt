package com.baeldung.math.standardDeviation
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.pow

class StandardDeviationUnitTest {

    @Test
    fun `standard deviation using the math package`() {
        val dataset1 = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val dataset2 = doubleArrayOf(11.0, 14.0, 19.0, 23.0, 28.0, 30.0)

        assertEquals(1.707825127659933, standardDeviationUsingMathPackage(dataset1))
        assertEquals(6.914156170897181, standardDeviationUsingMathPackage(dataset2))
    }

    @Test
    fun `standard deviation using the apache commons math library`() {
        val dataset1 = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val dataset2 = doubleArrayOf(11.0, 14.0, 19.0, 23.0, 28.0, 30.0)

        assertEquals(1.707825127659933, calculateStandardDeviationUsingApacheCommonsMath(dataset1))
        assertEquals(6.914156170897181, calculateStandardDeviationUsingApacheCommonsMath(dataset2))
    }


    fun standardDeviationUsingMathPackage(dataset: DoubleArray): Double {
        val mean = dataset.average()
        val variance = dataset.map { (it - mean).pow(2) }.average()
        return Math.sqrt(variance)
    }

    fun calculateStandardDeviationUsingApacheCommonsMath(dataset: DoubleArray): Double {
        val sd = StandardDeviation(false)

        return sd.evaluate(dataset)
    }
}