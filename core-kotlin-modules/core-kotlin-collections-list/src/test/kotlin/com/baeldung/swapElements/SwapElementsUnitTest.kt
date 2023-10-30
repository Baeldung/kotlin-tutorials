package com.baeldung.swapElements

import org.junit.jupiter.api.Test
import java.util.Collections
import kotlin.test.assertEquals

class SwapElementsUnitTest {

    @Test
    fun `when using Collections swap, then get expected result`() {
        val myList = mutableListOf("A", "B", "C", "D", "E", "F")
        val expected = listOf("A", "E", "C", "D", "B", "F")
        Collections.swap(myList, 1, 4)
        assertEquals(expected, myList)
    }

    @Test
    fun `when swap by a temp variable, then get expected result`() {
        val myList = mutableListOf("A", "B", "C", "D", "E", "F")
        val expected = listOf("A", "E", "C", "D", "B", "F")
        val t = myList[1]
        myList[1] = myList[4]
        myList[4] = t
        assertEquals(expected, myList)
    }


    @Test
    fun `when swap using also(), then get expected result`() {
        val myList = mutableListOf("A", "B", "C", "D", "E", "F")
        val expected = listOf("A", "E", "C", "D", "B", "F")
        myList[1] = myList[4].also { myList[4] = myList[1] }
        assertEquals(expected, myList)
    }

    @Test
    fun `when swap using pair, then get expected result`() {
        val myList = mutableListOf("A", "B", "C", "D", "E", "F")
        val expected = listOf("A", "E", "C", "D", "B", "F")
        (myList[4] to myList[1]).apply {
            myList[1] = first
            myList[4] = second
        }
        assertEquals(expected, myList)
    }

    fun <T> swapElements(theList: MutableList<T>, idx1: Int, idx2: Int) {
        val t = theList[idx1]
        theList[idx1] = theList[idx2]
        theList[idx2] = t
    }

    @Test
    fun `when using swapElement(), then get expected result`() {
        val myList = mutableListOf("A", "B", "C", "D", "E", "F")
        val expected = listOf("A", "E", "C", "D", "B", "F")
        swapElements(myList, 1, 4)
        assertEquals(expected, myList)
    }


    fun <T> MutableList<T>.swap(idx1: Int, idx2: Int): MutableList<T> = apply {
        val t = this[idx1]
        this[idx1] = this[idx2]
        this[idx2] = t
    }

    @Test
    fun `when using the extension function, then get expected result`() {
        val myList = mutableListOf("A", "B", "C", "D", "E", "F")
        val expected = listOf("A", "E", "C", "D", "B", "F")
        assertEquals(expected, myList.swap(1, 4))
    }

    @Test
    fun `when using the extension function, then get expected result fluently`() {
        val myList = mutableListOf("A", "B", "C", "D", "E", "F")
        val result = myList.swap(1, 4)
          .windowed(2, 2)
        val expected = listOf(listOf("A", "E"), listOf("C", "D"), listOf("B", "F"))
        assertEquals(expected, result)
    }

}