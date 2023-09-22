package com.baeldung.math.sum


import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SumNaturalNumbersUnitTest {

    @Test
    fun `test sumUsingForLoop to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingForLoop(5))
        Assertions.assertEquals(5050, sumUsingForLoop(100))
    }

    @Test
    fun `test sumUsingWhileLoop to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingWhileLoop(5))
        Assertions.assertEquals(5050, sumUsingWhileLoop(100))
    }

    @Test
    fun `test sumUsingArithmeticProgressionFormula to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingArithmeticProgressionFormula(5))
        Assertions.assertEquals(5050, sumUsingArithmeticProgressionFormula(100))
    }

    @Test
    fun `test sumUsingRecursion to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingRecursion(5))
        Assertions.assertEquals(5050, sumUsingRecursion(100))
    }

    @Test
    fun `test sumUsingRangeAndSum to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingRangeAndSum(5))
        Assertions.assertEquals(5050, sumUsingRangeAndSum(100))
    }

    @Test
    fun `test sumUsingRangeAndReduce to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingRangeAndReduce(5))
        Assertions.assertEquals(5050, sumUsingRangeAndReduce(100))
    }

    @Test
    fun `test sumUsingRangeAndSumBy to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingRangeAndSumBy(5))
        Assertions.assertEquals(5050, sumUsingRangeAndSumBy(100))
    }

    @Test
    fun `test sumUsingRangeAndFold to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingRangeAndFold(5))
        Assertions.assertEquals(5050, sumUsingRangeAndFold(100))
    }

    @Test
    fun `test sumUsingSequence to calculate sum should give correct results`() {
        Assertions.assertEquals(15, sumUsingSequence(5))
        Assertions.assertEquals(5050, sumUsingSequence(100))
    }

}
