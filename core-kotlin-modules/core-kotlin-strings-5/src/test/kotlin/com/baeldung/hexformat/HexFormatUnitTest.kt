package com.baeldung.hexformat

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@OptIn(ExperimentalStdlibApi::class)
class HexFormatUnitTest {

    val prefixNumberFormat = HexFormat {
        number.prefix = "%"
    }

    val prefixRemoveLeadingZerosNumberFormat = HexFormat {
        number.removeLeadingZeros = true
    }

    val hexFormat = HexFormat {
        number {
            prefix = "0x"
            removeLeadingZeros = true
        }
    }

    val macAddressFormat = HexFormat {
        upperCase = true
        bytes.bytesPerGroup = 1
        bytes.groupSeparator = ":"
    }

    @Test
    fun `Return hexadecimal string when formatting byte value with HexFormat`() {
        val number: Byte = 63
        assertEquals("3f", number.toHexString())
        assertEquals("3f", number.toHexString(HexFormat.Default))
        assertEquals("%3f", number.toHexString(prefixNumberFormat))
        assertEquals("0x3f", number.toHexString(hexFormat))
    }

    @Test
    fun `Return hexadecimal string when formatting short value with HexFormat`() {
        val number: Short = 63
        assertEquals("003f", number.toHexString())
        assertEquals("003f", number.toHexString(HexFormat.Default))
        assertEquals("%003f", number.toHexString(prefixNumberFormat))
        assertEquals("3f", number.toHexString(prefixRemoveLeadingZerosNumberFormat))
        assertEquals("0x3f", number.toHexString(hexFormat))
    }

    @Test
    fun `Return hexadecimal string when formatting int value with HexFormat`() {
        val number: Int = 63
        assertEquals("0000003f", number.toHexString())
        assertEquals("0000003f", number.toHexString(HexFormat.Default))
        assertEquals("%0000003f", number.toHexString(prefixNumberFormat))
        assertEquals("3f", number.toHexString(prefixRemoveLeadingZerosNumberFormat))
        assertEquals("0x3f", number.toHexString(hexFormat))
    }

    @Test
    fun `Return hexadecimal string when formatting long value with HexFormat`() {
        val number: Long = 63
        assertEquals("000000000000003f", number.toHexString())
        assertEquals("000000000000003f", number.toHexString(HexFormat.Default))
        assertEquals("%000000000000003f", number.toHexString(prefixNumberFormat))
        assertEquals("3f", number.toHexString(prefixRemoveLeadingZerosNumberFormat))
        assertEquals("0x3f", number.toHexString(hexFormat))
    }

    @Test
    fun `Return number type when parsing hexadecimal string`() {
        assertEquals(63, "3f".hexToByte())
        assertThrows<IllegalArgumentException> { "%3f".hexToByte() }
        assertEquals(63, "%3f".hexToByte(prefixNumberFormat))


        assertEquals(63, "3f".hexToShort())
        assertThrows<IllegalArgumentException> { "%3f".hexToShort() }
        assertEquals(63, "%3f".hexToShort(prefixNumberFormat))

        assertEquals(63, "3f".hexToInt())
        assertThrows<IllegalArgumentException> { "%3f".hexToInt() }
        assertEquals(63, "%3f".hexToInt(prefixNumberFormat))

        assertEquals(63, "3f".hexToLong())
        assertThrows<IllegalArgumentException> { "%3f".hexToLong() }
        assertEquals(63, "%3f".hexToLong(prefixNumberFormat))
    }

    @Test
    fun `Return mac address hex value when formatting 6 bytes with mac address HexFormat`() {
        val macAddressBytes = byteArrayOf(2, 66, -64, -117, -14, 94)
        val macAddressHexValue = macAddressBytes.toHexString(macAddressFormat)
        assertEquals("02:42:C0:8B:F2:5E", macAddressHexValue)
    }

    @Test
    fun `Return mac address bytes when parsing mac address hex value with mac address HexFormat`() {
        val macAddressHexValue = "02:42:C0:8B:F2:5E"
        val macAddressBytes = macAddressHexValue.hexToByteArray(macAddressFormat)
        assertArrayEquals(byteArrayOf(2, 66, -64, -117, -14, 94), macAddressBytes)
    }
}
