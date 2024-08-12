package com.baeldung.okio

import okio.Buffer
import okio.GzipSink
import okio.buffer
import okio.sink
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals


class SinkUnitTest {
    @Test
    fun testOutputStream() {
        val inputBuffer = Buffer()
        inputBuffer.writeUtf8("Hello, World!")

        val outputStream = ByteArrayOutputStream()
        val sink = outputStream.sink()
        sink.write(inputBuffer, inputBuffer.size)

        val bytes = outputStream.toByteArray()
        assertEquals(13, bytes.size)
    }

    @Test
    fun testBufferedSink() {
        val outputStream = ByteArrayOutputStream()
        val sink = outputStream.sink().buffer()
        sink.writeUtf8("Hello, World!")
        sink.flush()

        val bytes = outputStream.toByteArray()
        assertEquals(13, bytes.size)
    }

    @Test
    fun testGzip() {
        val inputBuffer = Buffer()
        inputBuffer.writeUtf8("Hello, World!")

        val targetBuffer = Buffer()
        val sink = GzipSink(targetBuffer)
        sink.write(inputBuffer, inputBuffer.size)
        sink.close()

        // Turns out that ths data does not compress well
        assertEquals(33, targetBuffer.size)
    }

}