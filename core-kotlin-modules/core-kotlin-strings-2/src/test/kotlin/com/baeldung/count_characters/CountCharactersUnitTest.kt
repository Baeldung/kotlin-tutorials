package com.baeldung.count_characters

import org.junit.jupiter.api.Test
import java.util.regex.Pattern
import kotlin.test.assertEquals

class CountCharactersUnitTest {

    @Test
    fun `should count characters using count`() {
        val string = "hello world, baeldung"
        assertEquals(2, string.count { it == 'e' })
    }

    @Test
    fun `should count characters using length difference`() {
        val string = "hello world, baeldung"
        val counter = string.length - string.replace("e", "").length
        assertEquals(2, counter)
    }

    @Test
    fun `should count characters using patterns`() {
        val string = "hello world, baeldung"
        val matcher = Pattern.compile("e").matcher(string)
        var counter = 0
        while (matcher.find()) {
            counter++
        }
        assertEquals(2, counter)
    }

    @Test
    fun `should count characters using map`() {
        val string = "hello world, baeldung"
        val occurrencesMap = mutableMapOf<Char, Int>()
        for (c in string) {
            occurrencesMap.putIfAbsent(c, 0)
            occurrencesMap[c] = occurrencesMap[c]!! + 1
        }
        assertEquals(2, occurrencesMap['e'])
    }

    @Test
    fun `should count characters using iteration`() {
        val string = "hello world, baeldung"
        var counter = 0
        for (c in string) {
            if (c == 'e') {
                counter++
            }
        }
        assertEquals(2, counter)
    }
}