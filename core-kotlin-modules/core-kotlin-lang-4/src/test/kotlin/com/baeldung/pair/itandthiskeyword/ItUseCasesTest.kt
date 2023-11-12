package com.baeldung.pair.itandthiskeyword

import org.junit.Assert.assertEquals
import org.junit.Test

class ItUseCasesTest {

    fun filterEvenNumbers(numbers: List<Int>): List<Int> {
        return numbers.filter { it % 2 == 0 }
    }
    val numbers = listOf(1, 2, 3, 4, 5)
    val evenNumbers = filterEvenNumbers(numbers)

    fun sortNamesByLength(names: List<String>): List<String> {
        return names.sortedBy { it.length }
    }
    val names = listOf("Alice", "Bob", "Charlie")
    val sortedNames = sortNamesByLength(names)

    @Test
    fun testFilterEvenNumbers() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val expectedEvenNumbers = listOf(2, 4)
        val evenNumbers = filterEvenNumbers(numbers)
        assertEquals(expectedEvenNumbers, evenNumbers)
    }

    @Test
    fun testSortNamesByLength() {
        val names = listOf("Alice", "Bob", "Charlie")
        val expectedSortedNames = listOf("Bob", "Alice", "Charlie")
        val sortedNames = sortNamesByLength(names)
        assertEquals(expectedSortedNames, sortedNames)
    }


}