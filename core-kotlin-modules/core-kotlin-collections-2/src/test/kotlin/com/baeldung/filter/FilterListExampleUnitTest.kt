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
        assertTrue(list.contains("Germany"))
        assertTrue(list.contains("Brazil"))
        assertTrue(list.contains("Australia"))
    }

    @Test
    fun whenFilterToList_thenContainsElements() {
        val list = classUnderTest.filterToList()
        assertEquals(5, list.size)
        assertTrue(list.contains("United States"))
        assertTrue(list.contains("Canada"))
        assertTrue(list.contains("Germany"))
        assertTrue(list.contains("Brazil"))
        assertTrue(list.contains("Australia"))
    }

    @Test
    fun whenFilterNotList_thenContainsElements() {
        val list = classUnderTest.filterNotList()
        assertEquals(2, list.size)
        assertTrue(list.contains("India"))
        assertTrue(list.contains("Japan"))
    }

    @Test
    fun whenFilterNotToList_thenContainsElements() {
        val list = classUnderTest.filterNotToList()
        assertEquals(4, list.size)
        assertTrue(list.contains("United States"))
        assertTrue(list.contains("Canada"))
        assertTrue(list.contains("India"))
        assertTrue(list.contains("Japan"))
    }

    @Test
    fun whenFilterIndexedList_thenContainsElements() {
        val list = classUnderTest.filterIndexedList()
        assertEquals(2, list.size)
        assertTrue(list.contains("Germany"))
        assertTrue(list.contains("Australia"))
    }

    @Test
    fun whenFilterIndexedToList_thenContainsElements() {
        val list = classUnderTest.filterIndexedToList()
        assertEquals(4, list.size)
        assertTrue(list.contains("United States"))
        assertTrue(list.contains("Canada"))
        assertTrue(list.contains("Germany"))
        assertTrue(list.contains("Australia"))
    }

    @Test
    fun whenFilterIsInstanceList_thenContainsElements() {
        val list = classUnderTest.filterIsInstanceList()
        assertEquals(4, list.size)
        assertTrue(list.contains(49))
        assertTrue(list.contains(91))
        assertTrue(list.contains(81))
        assertTrue(list.contains(61))
    }

    @Test
    fun whenFilterIsInstanceToList_thenContainsElements() {
        val list = classUnderTest.filterIsInstanceToList()
        assertEquals(6, list.size)
        assertTrue(list.contains(1))
        assertTrue(list.contains(24))
        assertTrue(list.contains(49))
        assertTrue(list.contains(91))
        assertTrue(list.contains(81))
        assertTrue(list.contains(61))
    }

    @Test
    fun whenFilterNotNullList_thenContainsElements() {
        val list = classUnderTest.filterNotNullList()
        assertEquals(5, list.size)
        assertTrue(list.contains("Germany"))
        assertTrue(list.contains("India"))
        assertTrue(list.contains("Japan"))
        assertTrue(list.contains("Brazil"))
        assertTrue(list.contains("Australia"))

    }

    @Test
    fun whenFilterNotNullToList_thenContainsElements() {
        val list = classUnderTest.filterNotNullToList()
        assertEquals(7, list.size)
        assertTrue(list.contains("United States"))
        assertTrue(list.contains("Canada"))
        assertTrue(list.contains("Germany"))
        assertTrue(list.contains("India"))
        assertTrue(list.contains("Japan"))
        assertTrue(list.contains("Brazil"))
        assertTrue(list.contains("Australia"))
    }

    @Test
    fun whenFilterUsingIterator_thenContainsElements() {
        val list = classUnderTest.filterUsingIterator()
        assertEquals(2, list.size)
        assertTrue(list.contains("India"))
        assertTrue(list.contains("Japan"))
    }

    @Test
    fun whenFilterUsingRemoveAll_thenContainsElements() {
        val list = classUnderTest.filterUsingRemoveAll()
        assertEquals(2, list.size)
        assertTrue(list.contains("India"))
        assertTrue(list.contains("Japan"))
    }

    @Test
    fun whenFilterUsingRetainAll_thenContainsElements() {
        val list = classUnderTest.filterUsingRetainAll()
        assertEquals(3, list.size)
        assertTrue(list.contains("Germany"))
        assertTrue(list.contains("Brazil"))
        assertTrue(list.contains("Australia"))
    }
}