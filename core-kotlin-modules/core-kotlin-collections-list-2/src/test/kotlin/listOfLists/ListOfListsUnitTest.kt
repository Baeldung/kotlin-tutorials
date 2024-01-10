package listOfLists

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ListOfListsUnitTest {

    @Test
    fun `Creates an immutable list of immutable lists using listOf()`() {
        val listOfLists = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))

        assertEquals(3, listOfLists.size)
        assertEquals(listOf(1, 2, 3), listOfLists[0])
        assertEquals(listOf(4, 5, 6), listOfLists[1])
        assertEquals(listOf(7, 8, 9), listOfLists[2])
    }

    @Test
    fun `Creates an immutable list of mutable lists using List constructor`() {
        val listOfLists = List(3) { MutableList<Int>(3) {0} }

        assertEquals(3, listOfLists.size)
        assertEquals(listOf(0, 0, 0), listOfLists[0])
        assertEquals(listOf(0, 0, 0), listOfLists[1])
        assertEquals(listOf(0, 0, 0), listOfLists[2])
    }

    @Test
    fun `Creates an immutable list of immutable lists using map method`() {
        val listOfLists = (0..2).map { _ -> (0..2).map { 0 } }

        assertEquals(3, listOfLists.size)
        assertEquals(listOf(0, 0, 0), listOfLists[0])
        assertEquals(listOf(0, 0, 0), listOfLists[1])
        assertEquals(listOf(0, 0, 0), listOfLists[2])
    }

    @Test
    fun `Creates an immutable list of mutable lists using map method`() {
        val listOfMutableLists = (0..2).map { mutableListOf<Int>() }

        assertEquals(3, listOfMutableLists.size)
        assertTrue(listOfMutableLists.all { it.isEmpty() })
    }
}