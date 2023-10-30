package com.baeldung.math.average

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class AverageUnitTest {

    val numbers = listOf(10, 2, 3, 40, 5)

    @Test
    fun `test averageUsingForLoop to calculate average should give correct results`() {
        val average: Double = averageUsingForLoop(numbers)
        assertEquals(12.0, average)
    }

    @Test
    fun `test averageUsingForEachLoop to calculate average should give correct results`() {
        val average: Double = averageUsingForEachLoop(numbers)
        assertEquals(12.0, average)
    }

    @Test
    fun `test averageUsingAverageFunction to calculate average should give correct results`() {
        val average: Double = numbers.average()
        assertEquals(12.0, average)
    }

    @Test
    fun `test averageUsingFold to calculate average should give correct results`() {
        val average: Double = averageUsingFold(numbers)
        assertEquals(12.0, average)
    }

    @Test
    fun `test averageUsingReduce to calculate average should give correct results`() {
        val average: Double = averageUsingReduce(numbers)
        assertEquals(12.0, average)
    }

    @Test
    fun `test averageUsingSumByDouble to calculate average should give correct results`() {
        val average: Double = averageUsingSumByDouble(numbers)
        assertEquals(12.0, average)
    }

    @Test
    fun `test averageUsingSequence to calculate average should give correct results`() {
        val average: Double = averageUsingSequence(numbers)
        assertEquals(12.0, average)
    }


    @Test
    fun `test averageUsingMapReduce to calculate average should give correct results`() {
        val average: Double = averageUsingMapReduce(numbers)
        assertEquals(12.0, average)
    }

}