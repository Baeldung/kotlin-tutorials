package com.baeldung.array

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ArrayAccessUnitTest {

    lateinit var sports: Array<String>

    @BeforeEach
    fun setUp() {
        sports = arrayOf("Soccer", "Cricket", "Rugby")
    }

    @Test
    fun `Given an array of strings, Then check read access to array`() {
        Assertions.assertEquals("Soccer", sports.get(0))
        Assertions.assertEquals("Cricket", sports.get(1));
        Assertions.assertEquals("Rugby", sports.get(2));
    }

    @Test
    fun `Given an array of strings, Then check read access to invalid index of array`() {
        Assertions.assertThrows(IndexOutOfBoundsException::class.java) {
            sports.get(100)
        }
        Assertions.assertThrows(IndexOutOfBoundsException::class.java) {
            sports.get(-1)
        }
    }

    @Test
    fun `Given an array of strings, Then modify and check member values`() {
        sports.set(0, "Football")
        Assertions.assertEquals("Football", sports[0])
    }

    @Test
    fun `Given an array of strings, Then modify value at invalid index`() {
        Assertions.assertThrows(IndexOutOfBoundsException::class.java) {
            sports.set(10, "Football")
        }
    }
}