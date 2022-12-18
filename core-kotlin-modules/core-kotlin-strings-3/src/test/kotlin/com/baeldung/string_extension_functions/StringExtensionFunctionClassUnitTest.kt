package com.baeldung.string_extension_functions

import com.baeldung.string.extension_functions.StringExtensionFunctionClass
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class StringExtensionFunctionClassUnitTest {

    private val stringExtensionFunctionClass = StringExtensionFunctionClass()

    @Test
    fun `using replace() method`(){
        val inputString0 = "Jelly"
        val inputString1 = "Kotlin Replace Program"
        assertEquals(stringExtensionFunctionClass.replaceMethod1(inputString0, 'l', 'z'), "Jezzy")
        assertEquals(stringExtensionFunctionClass.replaceMethod2(inputString1, "PROGRAM", "Examples", true), "Kotlin Replace Examples")
    }

    @Test
    fun `using uppercase() method`(){
        var str = "Extension Functions"
        var result = stringExtensionFunctionClass.uppercaseMethod(str)
        assertEquals(result, "EXTENSION FUNCTIONS")
    }

    @Test
    fun `using lowercase() method`(){
        var str = "Extension Functions"
        var result = stringExtensionFunctionClass.lowercaseMethod(str)
        assertEquals(result, "extension functions")
    }

    @Test
    fun `using toCharArray() method`(){
        val str = "functions"
        val chars = stringExtensionFunctionClass.toCharArrayMethod(str)
        val convertedString = String(chars)
        assertEquals(convertedString, "functions")
    }

    @Test
    fun `using substring() method`(){
        val str1 = "Hello World"
        val substring1 = stringExtensionFunctionClass.substringMethod1(str1, 6)
        val substring2 = stringExtensionFunctionClass.substringMethod2(str1, 0, 5)
        assertEquals(substring1,"World")
        assertEquals(substring2,"Hello")
    }

    @Test
    fun `using startsWith() method`(){
        val str = "extensionfunctions"
        assertTrue(stringExtensionFunctionClass.startsWithMethod1(str, "ext"))
        assertTrue(stringExtensionFunctionClass.startsWithMethod2(str,"fun", 9))
    }

    @Test
    fun `using endsWith() method`(){
        val str = "Kotlin"
        assertTrue(stringExtensionFunctionClass.endsWithMethod(str, "lin"))
    }

    @Test
    fun `using compareTo() method`(){
        var str1 = "Hello"
        var str2 = "Hello"
        assertEquals(stringExtensionFunctionClass.compareToMethod(str1,str2), 0)
        assertTrue(stringExtensionFunctionClass.compareToMethod(str1,str2) == 0)

        str2 = "Hallo"
        assertEquals(stringExtensionFunctionClass.compareToMethod(str1,str2), 4)
        assertTrue(stringExtensionFunctionClass.compareToMethod(str1,str2) > 0)

        assertEquals(stringExtensionFunctionClass.compareToMethod(str2, str1), -4)
        assertTrue(stringExtensionFunctionClass.compareToMethod(str2, str1) < 0)
    }

    @Test
    fun `using toByteArray() method`(){
        val str = "Hello"
        val byteArray = stringExtensionFunctionClass.toByteArrayMethod(str)
        assertEquals(byteArray.contentToString(), "[72, 101, 108, 108, 111]")
    }

    @Test
    fun `using capitalize() method`(){
        var str = "kotlin functions"
        assertEquals(stringExtensionFunctionClass.capitalizeMethod(str), "Kotlin functions")
    }
}