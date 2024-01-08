package com.baeldung.addListToSet

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AddListContentToSetUnitTest {

    @Test
    fun `test adding list contents to set programmatically`() {
        val list = listOf("apple", "banana", "orange")
        val set = addListToSet(list)
        assertEquals(setOf("apple", "banana", "orange"), set)
    }

    @Test
    fun `test adding list contents to set using addAll() method`() {
        val list = listOf("apple", "banana", "orange")
        val set = mutableSetOf<String>()
        set.addAll(list)
        assertEquals(setOf("apple", "banana", "orange"), set)
    }

    @Test
    fun `test adding list contents to set using toSet() method`() {
        val list = listOf("apple", "banana", "orange")
        val set = list.toSet()
        assertEquals(setOf("apple", "banana", "orange"), set)
    }

    @Test
    fun `test adding list contents to set using plus operator`() {
        val set = setOf<String>()
        val list = listOf("apple", "banana", "orange")
        val newSet = set + list
        assertEquals(setOf("apple", "banana", "orange"), newSet)
    }

    @Test
    fun `test adding list contents to set using union method`() {
        val set = setOf<String>()
        val list = listOf("apple", "banana", "orange")
        val newSet = set.union(list)
        assertEquals(setOf("apple", "banana", "orange"), newSet)
    }

    fun addListToSet(list: List<String>): Set<String> {
        val set = mutableSetOf<String>()
        for (element in list) {
            set.add(element)
        }
        return set
    }
}