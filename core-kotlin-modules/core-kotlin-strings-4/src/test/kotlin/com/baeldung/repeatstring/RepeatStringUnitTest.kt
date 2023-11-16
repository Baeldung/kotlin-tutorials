package com.baeldung.repeatstring

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RepeatStringUnitTest {

    @Test
    fun `repeat string n times using for loop`() {
        val string = "Hello World"
        val n = 3
        assertEquals("Hello WorldHello WorldHello World", repeatString(string, n))
    }

    @Test
    fun `repeat string n times using repeat() method`() {
        val str = "Hello World"
        val repeated = str.repeat(3)
        assertEquals("Hello WorldHello WorldHello World", repeated)
    }

    @Test
    fun `repeat string n times using stringbuilder method`() {
        val str = "Hello World"
        val repeated = StringBuilder().apply {
            repeat(3) {
                append(str)
            }
        }.toString()
        assertEquals("Hello WorldHello WorldHello World", repeated)
    }

    @Test
    fun `repeat string n times using CharArray and String constructor method`() {
        val str = "Hello World"
        val repeated = String(CharArray(str.length * 3) { str[it % str.length] })
        assertEquals("Hello WorldHello WorldHello World", repeated)
    }

    @Test
    fun `repeat string n times using Sequence and JoinToString method`() {
        val str = "Hello World"
        val repeated = generateSequence { str }.take(3).joinToString("")
        assertEquals("Hello WorldHello WorldHello World", repeated)
    }

    fun repeatString(string: String, n: Int): String {
        var result = ""
        for (i in 1..n) {
            result += string
        }
        return result
    }
}