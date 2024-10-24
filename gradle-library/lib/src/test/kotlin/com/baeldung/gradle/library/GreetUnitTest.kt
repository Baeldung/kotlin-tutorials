package com.baeldung.gradle.library

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GreetTest {
    @Test
    fun `test greet function`() {
        val result = greet("World")
        assertEquals("Hello, World!", result)
    }
}