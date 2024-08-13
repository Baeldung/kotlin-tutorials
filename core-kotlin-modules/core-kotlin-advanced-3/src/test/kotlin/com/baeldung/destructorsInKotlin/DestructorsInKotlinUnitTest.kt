package com.baeldung.destructorsInKotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.io.BufferedReader
import java.io.IOException


class DestructorsInKotlinUnitTest {
    @Test
    @Throws(IOException::class)
    fun `perform resource cleaning with try-finally block`() {
        val mockReader = mock(BufferedReader::class.java)
        `when`(mockReader.readLine()).thenReturn("Hello, Kotlin!", null)

        val content = readFile(mockReader)
        assertEquals("Hello, Kotlin!", content)

        verify(mockReader).close()
    }

    @Test
    @Throws(IOException::class)
    fun `perform resource cleaning with use`() {
        val mockReader = mock(BufferedReader::class.java)
        `when`(mockReader.readLine()).thenReturn("Hello, Kotlin!", null)

        val content = readFileUsingUse(mockReader)
        assertEquals("Hello, Kotlin!", content)

        verify(mockReader).close()
    }
}

@Throws(IOException::class)
fun readFile(reader: BufferedReader): String {
    try {
        val content = StringBuilder()
        var line: String?
        while ((reader.readLine().also { line = it }) != null) {
            content.append(line)
        }
        return content.toString()
    } finally {
        reader.close()
    }
}

@Throws(IOException::class)
fun readFileUsingUse(reader: BufferedReader): String {
    return reader.use { br ->
        val content = StringBuilder()
        var line: String?
        while ((br.readLine().also { line = it }) != null) {
            content.append(line)
        }
        content.toString()
    }
}

