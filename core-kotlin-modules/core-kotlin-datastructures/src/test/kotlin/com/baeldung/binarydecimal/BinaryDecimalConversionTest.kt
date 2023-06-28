package com.baeldung.binarydecimal

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.test.assertEquals

class BinaryDecimalConversionTest {

    @Test
    fun `binary to decimal using toInt() method`(){
        assertEquals(439, "110110111".toInt(2))
        assertEquals(109, "1101101".toInt(2))
        assertEquals(27, "11011".toInt(2))
        assertEquals(122979, "11110000001100011".toInt(2))
    }

    @Test
    fun `binary to decimal mathematical approach`(){

        assertEquals(439, binaryToDecimal("110110111"))
        assertEquals(109, binaryToDecimal("1101101"))
        assertEquals(27, binaryToDecimal("11011"))
        assertEquals(122979, binaryToDecimal("11110000001100011"))

    }

    fun binaryToDecimal(binary: String): Int{
        var binaryNumber = binary.toLong()
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (binaryNumber.toInt() != 0) {
            remainder = binaryNumber % 10
            binaryNumber /= 10
            decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
            ++i
        }
        return decimalNumber

    }

    @Test
    fun `decimal to binary mathematical approach`(){

        assertEquals(110110111, decimalToBinary(439))
        assertEquals(1101101, decimalToBinary(109))
        assertEquals(11011, decimalToBinary(27))
    }

    @Test
    fun `decima to binary using toBinaryString() method`(){
        assertEquals("110110111", Integer.toBinaryString(439))
        assertEquals("1101101", Integer.toBinaryString(109))
        assertEquals("11011", Integer.toBinaryString(27))
    }

    fun decimalToBinary(n: Int): Long {
        var decimal = n
        var binaryNumber: Long = 0
        var remainder: Int
        var i = 1

        while (decimal != 0) {
            remainder = decimal % 2
            decimal /= 2
            binaryNumber += (remainder * i).toLong()
            i *= 10
        }
        return binaryNumber
    }
}