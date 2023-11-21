package com.baeldung.gcd

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class GCDTests {

    fun calculateGCD(a: Int, b: Int): Int {
        var num1 = a
        var num2 = b
        while (num2 != 0) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    fun calculateGCDForListOfNumbers(numbers: List<Int>): Int {
        require(numbers.isNotEmpty()) { "List must not be empty" }
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            var num1 = result
            var num2 = numbers[i]
            while (num2 != 0) {
                val temp = num2
                num2 = num1 % num2
                num1 = temp
            }
            result = num1
        }
        return result
    }




    @Test
    fun testGCD() {
        assertEquals(6, calculateGCD(18, 48))
        assertEquals(1, calculateGCD(17, 5))
        assertEquals(9, calculateGCD(27, 18))
        assertEquals(15, calculateGCD(75, 45))
    }

    @Test
    fun testCalculateGCD1() {
        val result = calculateGCDForListOfNumbers(listOf(10, 20, 30))
        assertEquals(10, result)
    }
    @Test
    fun testCalculateGCD2() {
        val result = calculateGCDForListOfNumbers(listOf(21, 36))
        assertEquals(3, result)
    }
    @Test
    fun testCalculateGCD3() {
        val result = calculateGCDForListOfNumbers(listOf(15, 45, 75))
        assertEquals(15, result)
    }

    @Test
    fun testCalculateGCD4() {
        assertFailsWith<IllegalArgumentException> {
            calculateGCDForListOfNumbers(emptyList())
        }
    }
}