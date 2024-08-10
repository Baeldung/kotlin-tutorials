package com.baeldung.okio

import okio.*
import okio.ByteString.Companion.encodeUtf8
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class SourceUnitTest {
    @Test
    fun testInputStreamSource() {
        val inputStream = ByteArrayInputStream("Hello, World!".encodeUtf8().toByteArray())

        val source = inputStream.source()

        val buffer = Buffer()
        val read = source.read(buffer, 13)

        assertEquals(13, read)
        assertEquals("Hello, World!", buffer.readUtf8())
    }

    @Test
    fun testBufferedSource() {
        val inputStream = ByteArrayInputStream("Hello, World!".encodeUtf8().toByteArray())

        val source = inputStream.source().buffer()

        assertEquals("Hello, World!", source.readUtf8())
    }

    @Test
    fun testGzipSource() {
        val inputBuffer = Buffer()
        inputBuffer.writeUtf8("Hello, World!")

        val targetBuffer = Buffer()
        val sink = GzipSink(targetBuffer)
        sink.write(inputBuffer, inputBuffer.size)
        sink.close()

        val gzipSource = GzipSource(targetBuffer)
        assertEquals("Hello, World!", gzipSource.buffer().readUtf8())
    }
}