package com.baeldung.getcharacter

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GetCharacterUnitTest {

    @Test
    fun `should get character from string using indexing`() {
        val string = "Baeldung"
        assertEquals('l', string[3])
    }

    @Test
    fun `should get character from string using get`() {
        val string = "Baeldung"
        assertEquals('l', string.get(3))
    }

    @Test
    fun `should get first character from string`() {
        val string = "Baeldung"
        assertEquals('B', string.first())
    }

    @Test
    fun `should get last character from string`() {
        val string = "Baeldung"
        assertEquals('g', string.last())
    }

    @Test
    fun `should get single character from string`() {
        val string = "A"
        assertEquals('A', string.single())
    }

    @Test
    fun `should get single character by converting to array`() {
        val string = "Baeldung"
        val toCharArray = string.toCharArray()
        assertEquals('l', toCharArray[3])
    }

    @Test
    fun `should get single character by subsequence`() {
        val string = "Baeldung"
        val substring = string.subSequence(3, 4).single()
        assertEquals('l', substring)
    }
}
