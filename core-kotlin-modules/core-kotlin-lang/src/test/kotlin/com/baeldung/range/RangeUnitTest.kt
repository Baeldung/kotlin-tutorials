package com.baeldung.range

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RangeUnitTest {

    @Test
    fun testRange() {
        assertEquals(listOf(1,2,3), (1.rangeTo(3).toList()))
    }

    @Test
    fun testDownTo(){
        assertEquals(listOf(3,2,1), (3.downTo(1).toList()))
    }

    @Test
    fun testUntil(){
        assertEquals(listOf(1,2), (1.until(3).toList()))
    }
}