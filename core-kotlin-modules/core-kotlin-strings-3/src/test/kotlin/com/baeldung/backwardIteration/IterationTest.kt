package com.baeldung.backwardIteration

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class IterationTest {
    fun iterateStringWithForLoop(): String {
        val text = "Hello, Kotlin"
        val reversedText = StringBuilder()
        for (i in text.length - 1 downTo 0) {
            val char = text[i]
            reversedText.append(char)
        }
        return reversedText.toString()
    }

    @Test
    fun testIterateStringWithForLoop() {
        val mock = mockk<IterationTest>()
        every { mock.iterateStringWithForLoop() } returns "nittoK ,olleH"
        val result = mock.iterateStringWithForLoop()
        verify { mock.iterateStringWithForLoop() }
        assertEquals("nittoK ,olleH", result)
    }
}


