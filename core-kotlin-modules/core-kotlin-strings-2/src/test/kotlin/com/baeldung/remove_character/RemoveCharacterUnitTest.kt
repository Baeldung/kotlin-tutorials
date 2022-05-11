package com.baeldung.remove_character

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RemoveCharacterUnitTest {

    @Test
    fun `should remove character from string using replace`() {
        val string = "Ba.eldung"
        assertEquals("Baeldung", string.replace(".", ""))
    }

    @Test
    fun `should remove character from string using filter`() {
        val string = "Ba.eldung"
        assertEquals("Baeldung", string.filterNot { it == '.' })
    }

    @Test
    fun `should remove character from string using deleteAt`() {
        val string = "Ba.eldung"
        val stringBuilder = StringBuilder(string)
        assertEquals("Baeldung", stringBuilder.deleteAt(2).toString())
    }

    @Test
    fun `should remove character by using removeRange`() {
        val string = "Ba.eldung"
        assertEquals("Baeldung", string.removeRange(2, 3))
    }

    @Test
    fun `should remove last character from string`() {
        val string = "Baeldung"
        assertEquals("Baeldun", string.removeSuffix("g"))
        assertEquals("Baeldung", string.removePrefix("Z"))
    }

    @Test
    fun `should remove first character from string`() {
        val string = "Baeldung"
        assertEquals("aeldung", string.removePrefix("B"))
        assertEquals("Baeldung", string.removePrefix("Z"))
    }
}
