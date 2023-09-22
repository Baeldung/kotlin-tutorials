package com.baeldung.extfunction


import org.junit.Assert.assertEquals
import org.junit.Test

class ExtFunTests {

    fun String.reverse(): String {
        return this.reversed()
    }

    @Test
    fun testReverseWithJUnit() {
        val emptyString = ""
        assertEquals("", emptyString.reverse())
        val singleCharString = "a"
        assertEquals("a", singleCharString.reverse())
        val multiCharString = "Hello, World!"
        assertEquals("!dlroW ,olleH", multiCharString.reverse())
    }

}