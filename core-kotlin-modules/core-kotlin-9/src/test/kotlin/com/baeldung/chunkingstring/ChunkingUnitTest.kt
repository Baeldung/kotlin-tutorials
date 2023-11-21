package com.baeldung.chunkingstring

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ChunkingUnitTest {

    private val inputString = "HelloWorld"

    @Test
    fun testBasicChunked() {
        val chunkSize = 3
        val expected = listOf("Hel", "loW", "orl", "d")
        val actual = inputString.chunked(chunkSize)
        assertEquals(expected, actual, "Basic chunked method failed")
    }

    @Test
    fun testChunkedWithPadEnd() {
        val chunkSize = 3
        val paddedInput = inputString.padEnd(inputString.length + chunkSize - (inputString.length % chunkSize), ' ')
        val expected = listOf("Hel", "loW", "orl", "d  ")
        val actual = paddedInput.chunked(chunkSize)
        assertEquals(expected, actual, "Chunked with padEnd method failed")
    }

    @Test
    fun testWindowedChunking() {
        val chunkSize = 3
        val expected = listOf("Hel", "loW", "orl", "d")
        val actual = inputString.windowed(size = chunkSize, step = chunkSize, partialWindows = true)
        assertEquals(expected, actual, "Windowed chunking failed")
    }

    @Test
    fun testManualChunking() {
        val chunkSize = 3
        val expected = listOf("Hel", "loW", "orl", "d")
        val actual = mutableListOf<String>()
        for (i in 0 until inputString.length step chunkSize) {
            val end = (i + chunkSize).coerceAtMost(inputString.length)
            actual.add(inputString.substring(i, end))
        }
        assertEquals(expected, actual, "Manual chunking failed")
    }
}