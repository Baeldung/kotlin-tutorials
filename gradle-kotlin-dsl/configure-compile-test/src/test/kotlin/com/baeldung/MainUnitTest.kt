package com.baeldung

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MainUnitTest {

    @Test
    fun `just test for bytecode`() {
        Assertions.assertEquals("com.baeldung.MainUnitTest", MainUnitTest::class.qualifiedName)
    }
}