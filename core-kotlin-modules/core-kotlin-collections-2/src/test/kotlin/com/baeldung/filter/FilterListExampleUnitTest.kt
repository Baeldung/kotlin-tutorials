package com.baeldung.filter

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FilterListExampleUnitTest {

    private val classUnderTest: FilterListExample = FilterListExample()

    @Test
    fun whenFilterList_thenContainsElements() {
        val list = classUnderTest.filterList()
        assertEquals(3, list.size)
        assertTrue(list.containsAll(listOf("Germany","Brazil","Australia")))
    }

    @Test
    fun whenFilterToList_thenContainsElements() {
        val list = classUnderTest.filterToList()
        assertEquals(5, list.size)
        assertTrue(list.containsAll(listOf("United States","Canada","Germany","Brazil","Australia")))
    }

    @Test
    fun whenFilterNotList_thenContainsElements() {
        val list = classUnderTest.filterNotList()
        assertEquals(2, list.size)
        assertTrue(list.containsAll(listOf("India","Japan")))
    }

    @Test
    fun whenFilterNotToList_thenContainsElements() {
        val list = classUnderTest.filterNotToList()
        assertEquals(4, list.size)
        assertTrue(list.containsAll(listOf("United States","Canada","India","Japan")))
    }

    @Test
    fun whenFilterIndexedList_thenContainsElements() {
        val list = classUnderTest.filterIndexedList()
        assertEquals(2, list.size)
        assertTrue(list.containsAll(listOf("Germany","Australia")))
    }

    @Test
    fun whenFilterIndexedToList_thenContainsElements() {
        val list = classUnderTest.filterIndexedToList()
        assertEquals(4, list.size)
        assertTrue(list.containsAll(listOf("United States","Canada","Germany","Australia")))
    }

    @Test
    fun whenFilterIsInstanceList_thenContainsElements() {
        val list = classUnderTest.filterIsInstanceList()
        assertEquals(4, list.size)
        assertTrue(list.containsAll(listOf(49,91,81,61)))
    }

    @Test
    fun whenFilterIsInstanceToList_thenContainsElements() {
        val list = classUnderTest.filterIsInstanceToList()
        assertEquals(6, list.size)
        assertTrue(list.containsAll(listOf(1,24,49,91,81,61)))
    }

    @Test
    fun whenFilterNotNullList_thenContainsElements() {
        val list = classUnderTest.filterNotNullList()
        assertEquals(5, list.size)
        assertTrue(list.containsAll(listOf("Germany","India","Japan","Brazil","Australia")))
    }

    @Test
    fun whenFilterNotNullToList_thenContainsElements() {
        val list = classUnderTest.filterNotNullToList()
        assertEquals(7, list.size)
        assertTrue(list.containsAll(listOf("United States","Canada","Germany","India","Japan","Brazil","Australia")))
    }

    @Test
    fun whenFilterUsingIterator_thenContainsElements() {
        val list = classUnderTest.filterUsingIterator()
        assertEquals(2, list.size)
        assertTrue(list.containsAll(listOf("India","Japan")))
    }

    @Test
    fun whenFilterUsingRemoveAll_thenContainsElements() {
        val list = classUnderTest.filterUsingRemoveAll()
        assertEquals(2, list.size)
        assertTrue(list.containsAll(listOf("India","Japan")))
    }

    @Test
    fun whenFilterUsingRetainAll_thenContainsElements() {
        val list = classUnderTest.filterUsingRetainAll()
        assertEquals(3, list.size)
        assertTrue(list.containsAll(listOf("Germany","Brazil","Australia")))
    }
}