package com.baeldung.extfunction


import org.junit.Assert.assertEquals
import org.junit.Test

class ExtFunTests {

    fun String.reverse(): String {
        return this.reversed()
    }

    @Test
    fun testEmptyStringReverse() {
        val emptyString = ""
        assertEquals("", emptyString.reverse())
    }

    @Test
    fun testSingleCharStringReverse() {
        val singleCharString = "a"
        assertEquals("a", singleCharString.reverse())
    }

    @Test
    fun testMultiCharStringReverse() {
        val multiCharString = "Hello, World!"
        assertEquals("!dlroW ,olleH", multiCharString.reverse())
    }


}