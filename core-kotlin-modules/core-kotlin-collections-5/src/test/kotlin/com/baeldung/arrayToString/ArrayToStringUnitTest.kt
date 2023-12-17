package com.baeldung.arrayToString

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArrayToStringUnitTest {

    @Test
    fun `when using contentToString() then return the content as string`() {
        val intArray: IntArray = intArrayOf(9, 8, 7, 6)
        assertEquals("[9, 8, 7, 6]", intArray.contentToString())

        val stringArray: Array<String> = arrayOf("java", "kotlin", "scala")
        assertEquals("[java, kotlin, scala]", stringArray.contentToString())
    }

    @Test
    fun `when using reduce() then return the content by applying the function`() {
        val stringArray: Array<String> = arrayOf("java", "kotlin", "scala")

        val result = stringArray.reduce { result, nr -> "$result; $nr" }

        assertEquals("java; kotlin; scala", result)
    }

    @Test
    fun `when using map() and reduce() then return the content by applying the function`() {
        val intArray: IntArray = intArrayOf(9, 8, 7, 6)

        val result = intArray
          .map { it.toString() }
          .reduce { result, nr -> "$result; $nr" }

        assertEquals("9; 8; 7; 6", result)
    }

    @Test
    fun `when using fold() then return the content by applying the function`() {
        val intArray: IntArray = intArrayOf(9, 8, 7, 6)

        val result = intArray.fold("") { result, nr ->
            if (result.isEmpty()) "$nr" else "$result; $nr"
        }

        assertEquals("9; 8; 7; 6", result)
    }


    @Test
    fun `when using a for loop then return the content concatenated`() {
        val intArray: IntArray = intArrayOf(9, 8, 7, 6)

        var result = intArray[0].toString()
        for (i in 1 ..< intArray.size) {
            result += "; ${intArray[i]}"
        }

        assertEquals("9; 8; 7; 6", result)
    }

}