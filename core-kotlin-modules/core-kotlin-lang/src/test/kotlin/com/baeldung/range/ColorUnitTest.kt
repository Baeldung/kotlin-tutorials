package com.baeldung.range

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ColorUnitTest {

    @Test
    fun testEnumRange() {

        println(Color.values().toList());
        val red = Color.RED
        val yellow = Color.YELLOW
        val range = red..yellow

        assertTrue { range.contains(Color.MAGENTA) }
        assertFalse { range.contains(Color.BLUE) }
    }
}