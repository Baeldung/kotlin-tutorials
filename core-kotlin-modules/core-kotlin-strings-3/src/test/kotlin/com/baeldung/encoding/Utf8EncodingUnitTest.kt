package com.baeldung.encoding

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.CharBuffer

class Utf8EncodingUnitTest {
    
    private val byteArray = byteArrayOf(84, 104, 97, 116, 32, 119, 105, 108, 108, 32, 99, 111, 115, 116, 32, -30, -126, -84, 49, 48, 46) 
    private val charArray = charArrayOf('T', 'h', 'a', 't', ' ', 'w', 'i', 'l', 'l', ' ', 'c', 'o', 's', 't', ' ', '€', '1', '0', '.') 
    private val expectedString = "That will cost €10." 

    @Test
    fun givenUnicodeString_whenAsciiOrUtf8Encoding_itIsOnlyPreservedWithUtf8() {
        val originalString = "That will cost €10."
        val stringAsByteArray = originalString.toByteArray()

        val utf8String = String(stringAsByteArray, Charsets.UTF_8)
        val asciiString = String(stringAsByteArray, Charsets.US_ASCII)

        Assertions.assertEquals(originalString, utf8String)
        Assertions.assertNotEquals(originalString, asciiString)
        Assertions.assertEquals("That will cost ���10.", asciiString)
    }

    @Test
    fun givenByteArray_whenGivenToDefaultStringConstructor_itIsUtf8Encoded() {
        val utf8String = String(byteArray)

        Assertions.assertEquals(expectedString, utf8String)
    }

    @Test
    fun givenByteArray_whenGivenToStringConstructorWithCharset_itIsUtf8Encoded() {
        val utf8String = String(byteArray, Charsets.UTF_8)

        Assertions.assertEquals(expectedString, utf8String)
    }

    @Test
    fun givenByteArray_whenUsingToStringWithCharset_itIsUtf8Encoded() {
        val utf8StringDefault = byteArray.toString()
        val utf8StringExplicit = byteArray.toString(Charsets.UTF_8)

        Assertions.assertNotEquals(expectedString, utf8StringDefault)
        Assertions.assertEquals(expectedString, utf8StringExplicit)
    }

    @Test
    fun givenCharArray_whenEncodingAndDecoding_itIsUtf8Encoded() {
        val encodedCharBuffer = Charsets.UTF_8.encode(CharBuffer.wrap(charArray))
        val utf8String = Charsets.UTF_8.decode(encodedCharBuffer).toString()

        Assertions.assertEquals(expectedString, utf8String)
    }
}
