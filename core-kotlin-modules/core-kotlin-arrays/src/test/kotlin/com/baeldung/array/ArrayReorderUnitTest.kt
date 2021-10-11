package com.baeldung.array

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

class ArrayReorderUnitTest {
    lateinit var numbers: Array<Int>

    @BeforeEach
    fun setUp() {
        numbers = arrayOf(3, 2, 50, 15, 10, 1)
    }

    @Test
    fun `Given an array of int values, Then check the values on complete array reversal`() {
        numbers.reverse()
        Assertions.assertEquals(1, numbers[0])
        Assertions.assertEquals(10, numbers[1])
        Assertions.assertEquals(15, numbers[2])
        Assertions.assertEquals(50, numbers[3])
        Assertions.assertEquals(2, numbers[4])
        Assertions.assertEquals(3, numbers[5])
    }

    @Test
    fun `Given an array of int values, Then check the values on partial array reversal`() {
        numbers.reverse(4, 6)
        Assertions.assertEquals(3, numbers[0])
        Assertions.assertEquals(2, numbers[1])
        Assertions.assertEquals(50, numbers[2])
        Assertions.assertEquals(15, numbers[3])
        Assertions.assertEquals(1, numbers[4])
        Assertions.assertEquals(10, numbers[5])
    }

    @Test
    fun `Given an array of int values, Then check the values on complete array sort`() {
        numbers.sort()
        Assertions.assertEquals(1, numbers[0])
        Assertions.assertEquals(2, numbers[1])
        Assertions.assertEquals(3, numbers[2])
        Assertions.assertEquals(10, numbers[3])
        Assertions.assertEquals(15, numbers[4])
        Assertions.assertEquals(50, numbers[5])
    }

    @Test
    fun `Given an array of int values, Then check the values on partial array sort after an index`() {
        numbers.sort(4)
        Assertions.assertEquals(3, numbers[0])
        Assertions.assertEquals(2, numbers[1])
        Assertions.assertEquals(50, numbers[2])
        Assertions.assertEquals(15, numbers[3])
        Assertions.assertEquals(1, numbers[4])
        Assertions.assertEquals(10, numbers[5])
    }

    @Test
    fun `Given an array of int values, Then check the values on partial array sort between indices`() {
        numbers.sort(0, 2)
        Assertions.assertEquals(2, numbers[0])
        Assertions.assertEquals(3, numbers[1])
        Assertions.assertEquals(50, numbers[2])
        Assertions.assertEquals(15, numbers[3])
        Assertions.assertEquals(10, numbers[4])
        Assertions.assertEquals(1, numbers[5])
    }

    @Test
    fun `Given an array of int values, Then check when shuffled with a constant seed`() {
        numbers.shuffle(Random(2))
        Assertions.assertEquals(1, numbers[0])
        Assertions.assertEquals(3, numbers[1])
        Assertions.assertEquals(50, numbers[2])
        Assertions.assertEquals(15, numbers[3])
        Assertions.assertEquals(10, numbers[4])
        Assertions.assertEquals(2, numbers[5])
    }
}