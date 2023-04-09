package com.baeldung.encoding

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.CharBuffer

class Utf8EncodingTest {

    @Test
    fun givenUnicodeString_whenAsciiOrUtf8Encoding_itIsOnlyPreservedWithUtf8() {
        val originalString = "That will cost €10."
        val byteArray = originalString.toByteArray()

        val utf8String = String(byteArray, Charsets.UTF_8)
        val asciiString = String(byteArray, Charsets.US_ASCII)

        Assertions.assertEquals(originalString, utf8String)
        Assertions.assertNotEquals(originalString, asciiString)
        Assertions.assertEquals("That will cost ���10.", asciiString)
    }

    @Test
    fun givenByteArray_whenGivenToDefaultStringConstructor_itIsUtf8Encoded() {
        val originalString = "That will cost €10."
        val byteArray = originalString.toByteArray()

        val utf8String = String(byteArray)

        Assertions.assertEquals(originalString, utf8String)
    }

    @Test
    fun givenByteArray_whenGivenToStringConstructorWithCharset_itIsUtf8Encoded() {
        val originalString = "That will cost €10."
        val byteArray = originalString.toByteArray()

        val utf8String = String(byteArray, Charsets.UTF_8)

        Assertions.assertEquals(originalString, utf8String)
    }

    @Test
    fun givenByteArray_whenUsingToStringWithCharset_itIsUtf8Encoded() {
        val originalString = "That will cost €10."
        val byteArray = originalString.toByteArray()

        val utf8StringDefault = byteArray.toString()
        val utf8StringExplicit = byteArray.toString(Charsets.UTF_8)

        Assertions.assertNotEquals(originalString, utf8StringDefault)
        Assertions.assertEquals(originalString, utf8StringExplicit)
    }

    @Test
    fun givenCharArray_whenEncodingAndDecoding_itIsUtf8Encoded() {
        val charArray = charArrayOf('T', 'h', 'a', 't', ' ', 'w', 'i', 'l', 'l', ' ', 'c', 'o', 's', 't', ' ', '€', '1', '0', '.')

        val encodedCharBuffer = Charsets.UTF_8.encode(CharBuffer.wrap(charArray))
        val utf8String = Charsets.UTF_8.decode(encodedCharBuffer).toString()

        Assertions.assertEquals("That will cost €10.", utf8String)
    }

    @Test
    fun givenString_whenConvertedToByteArrayThenToString_itIsUtf8Encoded() {
        val originalString = "That will cost €10."

        val byteArray = originalString.toByteArray()
        val utf8String = byteArray.toString(Charsets.UTF_8)

        Assertions.assertEquals("That will cost €10.", utf8String)
    }
}