package com.baeldung.string_extension_functions

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class StringExtensionFunctionClassUnitTest {


    @Test
    fun `using replace() method`(){
        val inputString0 = "Jelly"
        val inputString1 = "Kotlin Replace Program"
        assertEquals(inputString0.replace( 'l', 'z'), "Jezzy")
        assertEquals(inputString0.replace('j', 'P', true), "Pelly")
        assertEquals(inputString0.replace('j', 'P'), "Jelly")
        assertEquals(inputString1.replace("PROGRAM", "Examples", true), "Kotlin Replace Examples")
    }

    @Test
    fun `using uppercase() method`(){
        var str = "Extension Functions"
        var result = str.uppercase()
        assertEquals(result, "EXTENSION FUNCTIONS")
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
        assertEquals(convertedString, "functions")
    }

    @Test
    fun `using substring() method`(){
        val str1 = "Hello World"
        val substring1 = str1.substring(6)
        val substring2 = str1.substring(0, 5)
        assertEquals(substring1,"World")
        assertEquals(substring2,"Hello")
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
        assertEquals(str1.compareTo(str2), 0)
        assertTrue(str1.compareTo(str2) == 0)

        str2 = "Hallo"
        assertTrue(str1.compareTo(str2) > 0)

        assertTrue(str2.compareTo(str1) < 0)
    }

    @Test
    fun `using toByteArray() method`(){
        val str = "Hello"
        val byteArray = str.toByteArray()
        assertEquals(byteArray.contentToString(), "[72, 101, 108, 108, 111]")
    }

    @Test
    fun `using capitalize() method`(){
        var str = "kotlin functions"
        assertEquals(str.capitalize(), "Kotlin functions")
    }
}