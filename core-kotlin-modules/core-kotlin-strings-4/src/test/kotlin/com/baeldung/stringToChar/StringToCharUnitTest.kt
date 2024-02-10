package com.baeldung.stringToChar

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StringToCharUnitTest {

    @Test
    fun `converts string to char using get method`(){
        val str = "H"
        val char = str.get(0)

        assertEquals('H', char)
    }

    @Test
    fun `converts string to char using index operator method`(){
        val str = "H"
        val char = str[0]

        assertEquals('H', char)
    }

    @Test
    fun `converts string to char using single method`(){
        val str = "H"
        val char = str.single()

        assertEquals('H', char)
    }

    @Test
    fun `converts string to char using first method`(){
        val str = "H"
        val char = str.first()

        assertEquals('H', char)
    }

    @Test
    fun `converts string to char using toCharArray method`(){
        val str = "H"
        val charArray = str.toCharArray()
        val char = charArray[0]

        assertEquals('H', char)
    }
}