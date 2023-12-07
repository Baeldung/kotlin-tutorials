package com.baeldung.convertStringToByteArray

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ConvertStringToByteArrayUnitTest {

    @Test
    fun `should convert string to byte array`() {
        val string = "Hello world"
        val byteArray = string.toByteArray()
        assertThat(byteArray).isEqualTo(byteArrayOf(72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100))
    }

    @Test
    fun `should convert string to byte array in provided encoding`() {
        val string = "Hello world"
        val byteArray = string.toByteArray(Charsets.ISO_8859_1)
        assertThat(byteArray).isEqualTo(byteArrayOf(72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100))
    }

    @Test
    fun `should convert substring to byte array`() {
        val string = "Hello world"
        val byteArray = string.encodeToByteArray(0, 5)
        assertThat(byteArray).isEqualTo(byteArrayOf(72, 101, 108, 108, 111))
    }

    @Test
    fun `should convert byte array to string`() {
        val byteArray = byteArrayOf(72, 101, 108, 108, 111)
        val string = String(byteArray)
        assertThat(string).isEqualTo("Hello")
    }
}