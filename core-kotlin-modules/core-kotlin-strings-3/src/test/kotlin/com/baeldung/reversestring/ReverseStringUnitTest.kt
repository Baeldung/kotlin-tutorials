package com.baeldung.reversestring
import org.junit.Assert.assertEquals
import org.junit.Test

class ReverseStringUnitTest {

    @Test
    fun `reverse string using custom method`() {
        val string = "this is a string"
        val expected = "ecnetnes a si siht"
        assertEquals(expected, reverseString(string))
    }

    @Test
    fun `reverse string using string reversed() method`() {
        val string = "this is a string"
        val expected = "ecnetnes a si siht"
        assertEquals(expected, string.reversed())
    }

    @Test
    fun `reverse string using stringbuilder reverse() method`() {
        val string = "this is a string"
        val expected = "ecnetnes a si siht"
        assertEquals(expected, StringBuilder(string).reverse().toString())
    }

    @Test
    fun `reverse string using recursion method`() {
        val string = "this is a string"
        val expected = "ecnetnes a si siht"
        assertEquals(expected, reverseStringRecursively(string))
    }
}

fun reverseString(string: String): String {
    var reversedString = ""
    for (i in string.length - 1 downTo 0) {
        reversedString += string[i]
    }
    return reversedString
}

fun reverseStringRecursively(string: String): String {
    return if (string.isEmpty()) {
        ""
    } else {
        reverseStringRecursively(string.substring(1)) + string[0]
    }
}