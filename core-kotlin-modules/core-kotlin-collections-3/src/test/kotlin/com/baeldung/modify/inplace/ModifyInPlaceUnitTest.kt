package com.baeldung.modify.inplace

import com.baeldung.modify.inplace.ModifyInPlace.mapInPlace
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ModifyInPlaceUnitTest {

    private val EXPECTED_LIST = listOf(1, 0, 3, 0, 5, 0, 7, 0, 9, 0)

    @Test
    fun `Given a list, when replaceEvenNumbersBy0Iterator is executed, even numbers are replaced by 0`() {
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        ModifyInPlace.replaceEvenNumbersBy0Iterator(list)

        assertEquals(EXPECTED_LIST, list)
    }

    @Test
    fun `Given a list, when replaceEvenNumbersBy0Direct is executed, even numbers are replaced by 0`() {
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        ModifyInPlace.replaceEvenNumbersBy0Direct(list)

        assertEquals(EXPECTED_LIST, list)
    }

    @Test
    fun `Given a list, when mapInPlace is executed with condition, even numbers are replaced by 0`() {
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        list.mapInPlace { if (it % 2 == 0) 0 else it }

        assertEquals(EXPECTED_LIST, list)
    }

    @Test
    fun `Given an array, when mapInPlace is executed with condition, even numbers are replaced by 0`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        array.mapInPlace { if (it % 2 == 0) 0 else it }

        val expectedArray = EXPECTED_LIST.toTypedArray()
        for (i in 0 until array.size) {
            assertEquals(expectedArray[i], array[i])
        }
    }

}