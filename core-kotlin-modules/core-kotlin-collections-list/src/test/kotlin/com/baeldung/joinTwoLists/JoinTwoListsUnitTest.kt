package com.baeldung.joinTwoLists

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test

class JoinTwoListsUnitTest {
    @Test
    fun `when the 1st list is mutable and using addAll() to join, then get expected result`() {
        val list1 = mutableListOf("A", "B", "C")
        val list2 = listOf("D", "E")

        list1.addAll(list2)
        assertEquals(listOf("A", "B", "C", "D", "E"), list1)

    }

    @Test
    fun `when the 1st list is mutable and using plusAssign operator to join, then get expected result`() {
        val list1 = mutableListOf("A", "B", "C")
        val list2 = listOf("D", "E")

        list1 += list2
        assertEquals(listOf("A", "B", "C", "D", "E"), list1)
    }

    @Test
    fun `when the 1st list is mutable and using plus operator to join, then get expected result`() {
        val list1 = mutableListOf("A", "B", "C")
        val list2 = listOf("D", "E")

        val result = list1 + list2
        assertEquals(listOf("A", "B", "C", "D", "E"), result)
        assertEquals(listOf("A", "B", "C"), list1) // list1 isn't modified
    }


    @Test
    fun `when using the plus operator to join two read-only Lists,then get expected result`() {
        val list1 = listOf("I", "II", "III")
        val list2 = listOf("IV", "V")

        val result = list1 + list2
        assertEquals(listOf("I", "II", "III", "IV", "V"), result)
        assertEquals(listOf("I", "II", "III"), list1)
    }

    @Test
    fun `when using plusAssign to join two read-only lists,then get expected exception`() {
        // plusAssign requires the target variable to be 'var'
        var list1 = listOf("I", "II", "III")
        val list2 = listOf("IV", "V")
        val list1Origin = list1

        list1 += list2
        assertEquals(listOf("I", "II", "III", "IV", "V"), list1)
        assertNotSame(list1Origin, list1)
    }


}