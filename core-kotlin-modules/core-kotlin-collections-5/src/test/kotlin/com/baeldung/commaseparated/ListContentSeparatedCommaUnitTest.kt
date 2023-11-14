package com.baeldung.commaseparated

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ListContentSeparatedCommaUnitTest {

    @Test
    fun `when using for loop the content is printed separated by comma`() {
        val myList = listOf("apple", "banana", "cherry", "date")
        var result = ""
        for (item in myList) {
            result += if (result.isEmpty()) item else ", $item"
        }
        assertEquals("apple, banana, cherry, date", result)
    }

    @Test
    fun `when using join content is printed separated by comma`() {
        val myList = listOf("apple", "banana", "cherry", "date")
        val result = myList.joinToString(", ")
        assertEquals("apple, banana, cherry, date", result)
    }

    @Test
    fun `when using reduce content is printed separated by comma`() {
        val myList = listOf("apple", "banana", "cherry", "date")
        val result = myList.reduce { acc, s -> "$acc, $s" }
        assertEquals("apple, banana, cherry, date", result)
    }
}