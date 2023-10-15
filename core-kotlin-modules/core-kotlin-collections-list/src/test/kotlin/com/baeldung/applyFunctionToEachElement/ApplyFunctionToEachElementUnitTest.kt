package com.baeldung.applyFunctionToEachElement

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

// function with a return value
fun String.reverseCase() = map { c -> if (c.isLowerCase()) c.uppercase() else c.lowercase() }.joinToString(separator = "")

// function returns Unit
fun String.printCaseReversed() = println("$this -> ${reverseCase()}")

class ApplyFunctionToEachElementUnitTest {
    val myList = listOf("a a a", "B B B", "c C c", "D d D", "e E E", "F F f")
    val expected = listOf("A A A", "b b b", "C c C", "d D d", "E e e", "f f F")

    @Test
    fun `when using map then get a new list containing expected elements`() {
        val result = myList.map { it.reverseCase() }
        assertEquals(expected, result)
    }

    @Test
    fun `when using replaceAll then elements are changed in-place`() {
        val mutableList = myList.toMutableList()
        assertEquals(myList, mutableList)

        mutableList.replaceAll { it.reverseCase() }
        assertEquals(expected, mutableList)
    }

    @Test
    fun `when using for each then the function gets executed for each element`() {
        myList.forEach { it.printCaseReversed() } // output the result of each element

        val result = mutableListOf<String>()
        myList.forEach { result += it.reverseCase() }
        assertEquals(expected, result)
    }

    @Test
    fun `when using on each then the function gets executed for each element`() {
        val result = myList.onEach { it.printCaseReversed() }
        assertSame(myList, result)

        // used in a call chain
        val resultList = myList.onEach { it.printCaseReversed() }
          .map { it.uppercase().replace(" ", ", ") }
        assertEquals(listOf("A, A, A", "B, B, B", "C, C, C", "D, D, D", "E, E, E", "F, F, F"), resultList)
    }

}