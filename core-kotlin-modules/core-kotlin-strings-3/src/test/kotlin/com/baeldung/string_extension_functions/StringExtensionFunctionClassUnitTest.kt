package com.baeldung.string_extension_functions

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class StringExtensionFunctionClassUnitTest {


    @Test
    fun `using replace() method`(){
        val inputString0 = "Jelly"
        val inputString1 = "Kotlin Replace Program"
        assertEquals("Jezzy", inputString0.replace( 'l', 'z'))
        assertEquals("Pelly", inputString0.replace('j', 'P', true))
        assertEquals("Jelly",inputString0.replace('j', 'P'))
        assertEquals("Kotlin Replace Examples", inputString1.replace("PROGRAM", "Examples", true))
    }

    @Test
    fun `using uppercase() method`(){
        var str = "Extension Functions"
        var result = str.uppercase()
        assertEquals("EXTENSION FUNCTIONS",result)
    }

    @Test
    fun `using lowercase() method`(){
        var str = "Extension Functions"
        var result = str.lowercase()
        assertEquals(result, "extension functions")
    }

    @Test
    fun `using toCharArray() method`(){
        val str = "functions"
        val chars = str.toCharArray()
        val convertedString = String(chars)
        assertEquals("functions", convertedString)
    }

    @Test
    fun `using substring() method`(){
        val str1 = "Hello World"
        val substring1 = str1.substring(6)
        val substring2 = str1.substring(0, 5)
        assertEquals("World",substring1)
        assertEquals("Hello",substring2)
    }

    @Test
    fun `using startsWith() method`(){
        val str = "extensionfunctions"
        assertTrue(str.startsWith("ext"))
        assertTrue(str.startsWith("fun", 9))
    }

    @Test
    fun `using endsWith() method`(){
        val str = "Kotlin"
        assertTrue(str.endsWith("lin"))
    }

    @Test
    fun `using compareTo() method`(){
        var str1 = "Hello"
        var str2 = "Hello"
        assertEquals(0,str1.compareTo(str2))
        assertTrue(str1.compareTo(str2) == 0)

        str2 = "Hallo"
        assertTrue(str1.compareTo(str2) > 0)

        assertTrue(str2.compareTo(str1) < 0)
    }

    @Test
    fun `using toByteArray() method`(){
        val str = "Hello"
        val byteArray = str.toByteArray()
        assertEquals("[72, 101, 108, 108, 111]",byteArray.contentToString())
    }

    @Test
    fun `using capitalize() method`(){
        var str = "kotlin functions"
        assertEquals("Kotlin functions",str.capitalize())
    }
}