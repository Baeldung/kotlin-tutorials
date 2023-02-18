package com.baeldung.textfile

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TextToFileUnitTest {

    private val filePath = "src/test/resources/text.out"

    private val textContent = "Nobel prize text content"

    @BeforeEach
    fun prepare() {
        File(filePath).apply {
            if (exists() && isFile) delete()
        }
    }

    @Test
    fun whenWrittenWithWriteText_thenCorrect() {
        val result = TextWriter.writeText(textContent, filePath)
        assertTrue(result.isSuccess)
        assertEquals(textContent, File(filePath).readText())
    }

    @Test
    fun whenWrittenWithBufferedWriter_thenCorrect() {
        val range = 0..100

        val seq = generateSequence("0") { current ->
            when (val next = (current.toInt() + 1)) {
                in range -> {
                    "$next"
                }

                else -> null
            }
        }

        val expected = range.joinToString(prefix = "", separator = "") { "$it" }
        val result = TextWriter.writeTextSequence(seq, filePath)

        assertTrue(result.isSuccess)
        assertEquals(expected, File(filePath).readText())
    }
}