package com.baeldung.lcm

import org.junit.Assert.assertEquals
import org.junit.Test

class LCMTests {
    fun findLCM(a: Int, b: Int): Int {
        val max = if (a > b) a else b
        var lcm = max
        while (true) {
            if (lcm % a == 0 && lcm % b == 0) {
                return lcm
            }
            lcm += max
        }
    }

    fun findLCMOfMultipleNumbers(numbers: List<Int>): Int {
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }



    @Test
    fun testLCM() {
        assertEquals(12, findLCM(3, 4))
        assertEquals(15, findLCM(5, 3))
        assertEquals(35, findLCM(7, 5))
        assertEquals(72, findLCM(24, 18))
    }

    @Test
    fun testLCMOfMultipleNumbers() {
        assertEquals(12, findLCMOfMultipleNumbers(listOf(3, 4)))
        assertEquals(15, findLCMOfMultipleNumbers(listOf(5, 3)))
        assertEquals(35, findLCMOfMultipleNumbers(listOf(7, 5, 5)))
        assertEquals(72, findLCMOfMultipleNumbers(listOf(24, 18, 12)))
    }


}