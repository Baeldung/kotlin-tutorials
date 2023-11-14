package com.baeldung.createByteArray

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CreateByteArrayUnitTest {

    @Test
    fun `create a byte array using signed byte`() {
        val byteArray = byteArrayOf(0x48, 101, 108, 108, 111)
        val string = String(byteArray)
        assertThat(string).isEqualTo("Hello")
    }

    @Test
    fun `create a byte array with negative values`() {
        val byteArray = byteArrayOf(Byte.MIN_VALUE, -1, 0, 1, Byte.MAX_VALUE)
        assertThat(byteArray)
            .hasSize(5)
            .containsExactly(-128,-1, 0, 1, 127)
    }

    @Test
    @OptIn(ExperimentalUnsignedTypes::class)
    fun `create a byte array using unsigned byte`(){
        val uByteArray = ubyteArrayOf(UByte.MIN_VALUE, 130U, 131u, UByte.MAX_VALUE)
        val intValues = uByteArray.map { it.toInt() }
        assertThat(intValues)
            .hasSize(4)
            .containsExactly(0, 130, 131, 255)
    }
}