package com.baeldung.arraytolist

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArrayToListUnitTest {

    @Test
    fun `asList maintains a reference to the original array`() {
        val array = intArrayOf(1, 2, 3, 4)
        val list = array.asList()

        assertEquals(listOf(1, 2, 3, 4), list)

        array[0] = 0
        assertEquals(listOf(0, 2, 3, 4), list)
    }

    @Test
    fun `toList copies the original array elements`() {
        val array = intArrayOf(1, 2, 3, 4)
        val list = array.toList()
        val mutable = array.toMutableList()

        assertEquals(listOf(1, 2, 3, 4), list)
        assertEquals(listOf(1, 2, 3, 4), mutable)

        array[0] = 0
        assertEquals(listOf(1, 2, 3, 4), list) // list didn't change

        mutable.add(5)
        assertEquals(listOf(1, 2, 3, 4, 5), mutable)
    }
}
