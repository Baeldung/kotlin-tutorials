package com.baeldung.array

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ArrayTraversalUnitTest {
    lateinit var dice: Array<Int>

    @BeforeEach
    fun setUp() {
        dice = arrayOf(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `Given a dice as an array of int values, Then check the face values using for loop`() {
        var expectedValue = 1
        for (faceValue in dice) {
            Assertions.assertEquals(expectedValue++, faceValue)
        }
    }

    @Test
    fun `Given a dice as an array of int values, Then check the face values using forEach`() {
        var expectedValue = 1
        dice.forEach { faceValue ->
            Assertions.assertEquals(expectedValue++, faceValue)
        }
    }

    @Test
    fun `Given a dice as an array of int values, Then check the face values using forEachIndexed`() {
        var expectedIndex = 0
        var expectedValue = 1
        dice.forEachIndexed { index, faceValue ->
            Assertions.assertEquals(expectedIndex++, index)
            Assertions.assertEquals(expectedValue++, faceValue)
        }
    }

    @Test
    fun `Given a dice as an array of int values, Then check the face values using iterator`() {
        var expectedValue = 1
        val iterator = dice.iterator()
        while (iterator.hasNext()) {
            val faceValue = iterator.next()
            Assertions.assertEquals(expectedValue++, faceValue)
        }
    }
}