package com.baeldung.removeTheFirstChar

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class RemoveTheFirstCharUnitTest {

    @Test
    fun `when calling drop() then get the expected result`() {
        val myStr = "0a b c d e"
        val result = myStr.drop(1)
        assertEquals("a b c d e", result)

        //works when the string is empty
        val result2 = "".drop(1)
        assertEquals("", result2)
    }

    @Test
    fun `when calling subString() then get the expected result`() {
        val myStr = "0a b c d e"
        val result = myStr.substring(1)
        assertEquals("a b c d e", result)

        //throw exception when the string is empty
        assertThrows<IndexOutOfBoundsException> {
            "".substring(1)
        }
    }

    @Test
    fun `when calling takeLast() then get the expected result`() {
        val myStr = "0a b c d e"
        val result = myStr.takeLast(myStr.length - 1) // or takeLast(myStr.lastIndex)
        assertEquals("a b c d e", result)

        //works when the string is empty
        val myEmpty = ""

        assertThrows<IllegalArgumentException> {
            myEmpty.takeLast(myEmpty.length - 1)
        }.also {
            assertEquals("Requested character count -1 is less than zero.", it.message)
        }

    }

    @Test
    fun `when using replace() then get the expected result`() {
        val myStr = "0a b c d e"
        val result = myStr.replace("^.".toRegex(), "")
        assertEquals("a b c d e", result)

        //works when the string is empty
        val result2 = "".replace("^.".toRegex(), "")
        assertEquals("", result2)
    }
}