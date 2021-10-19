package com.baeldung.array

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ArrayAssociateUnitTest {
    private val fruits = arrayOf("Pear", "Apple", "Papaya", "Banana")

    @Test
    fun `Given an array, Then build and check map using associate function`() {
        val nameVsLengthMap = fruits.associate { Pair(it, it.length) }
        Assertions.assertEquals(4, nameVsLengthMap.size)

        Assertions.assertEquals(4, nameVsLengthMap.get("Pear"))
        Assertions.assertEquals(5, nameVsLengthMap.get("Apple"))
        Assertions.assertEquals(6, nameVsLengthMap.get("Papaya"))
        Assertions.assertEquals(6, nameVsLengthMap.get("Banana"))
    }

    @Test
    fun `Given an array, Then build and check map using associateBy function`() {
        val lengthVsNameMap = fruits.associateBy { it.length }
        Assertions.assertEquals(3, lengthVsNameMap.size)

        Assertions.assertEquals("Pear", lengthVsNameMap.get(4))
        Assertions.assertEquals("Apple", lengthVsNameMap.get(5))
        Assertions.assertEquals("Banana", lengthVsNameMap.get(6))
    }

    @Test
    fun `Given an array, Then build and check map using associateWithTo function`() {
        val nameVsLengthMap = mutableMapOf("Pomegranate" to 11, "Pea" to 3)
        fruits.associateWithTo(nameVsLengthMap, { it.length })

        Assertions.assertEquals(6, nameVsLengthMap.size)

        Assertions.assertEquals(11, nameVsLengthMap.get("Pomegranate"))
        Assertions.assertEquals(3, nameVsLengthMap.get("Pea"))
        Assertions.assertEquals(4, nameVsLengthMap.get("Pear"))
        Assertions.assertEquals(5, nameVsLengthMap.get("Apple"))
        Assertions.assertEquals(6, nameVsLengthMap.get("Papaya"))
        Assertions.assertEquals(6, nameVsLengthMap.get("Banana"))
    }

    @Test
    fun `Given an array, Then build and check map using associateByTo function`() {
        val lengthVsNameMap = mutableMapOf(11 to "Pomegranate", 3 to "Pea")
        fruits.associateByTo(lengthVsNameMap, { it.length })

        Assertions.assertEquals(5, lengthVsNameMap.size)

        Assertions.assertEquals("Pomegranate", lengthVsNameMap.get(11))
        Assertions.assertEquals("Pea", lengthVsNameMap.get(3))
        Assertions.assertEquals("Pear", lengthVsNameMap.get(4))
        Assertions.assertEquals("Apple", lengthVsNameMap.get(5))
        Assertions.assertEquals("Banana", lengthVsNameMap.get(6))
    }
}