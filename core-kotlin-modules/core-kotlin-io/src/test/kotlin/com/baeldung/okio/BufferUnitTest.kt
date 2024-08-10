package com.baeldung.okio

import okio.Buffer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BufferUnitTest {
    @Test
    fun readAndWriteStringWithLength() {
        val input = "Hello, World!"

        val buffer = Buffer()
        buffer.writeInt(input.length)
        buffer.writeUtf8(input)

        val length = buffer.readInt()
        val string = buffer.readUtf8(length.toLong())

        assertEquals(input, string)
    }
}