package com.baeldung.range

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReverseRangeUnitTest {

    @Test
    fun reversedTest() {
        assertEquals(listOf(9, 6, 3), (1..9).reversed().step(3).toList())
    }
}