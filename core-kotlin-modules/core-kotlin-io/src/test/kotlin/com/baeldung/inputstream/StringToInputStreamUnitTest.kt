package com.baeldung.inputstream

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class StringToInputStreamUnitTest {

    @Test
    fun `when using ByteArrayInputStream(string-toByteArray()) should get expected inputstream`() {
        val theString = "Kotlin is awesome!"
        val inputStream = ByteArrayInputStream(theString.toByteArray())
        assertThat(inputStream).hasContent(theString)
    }

    @Test
    fun `when using string extension-byteInputStream should get expected inputstream`() {
        val theString = "Kotlin is awesome!"
        val inputStream = theString.byteInputStream()
        assertThat(inputStream).hasContent(theString)
    }
}