package com.baeldung.jvmfield

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class JvmSampleUnitTest {

    var sample = ""

    @BeforeEach
    fun setUp() {
        sample = JvmSample("Hello!").sampleText
    }

    @Test
    fun givenField_whenCheckValue_thenMatchesValue() {
        assertTrue(sample == "Hello!")
    }

    @Test
    fun givenStaticVariable_whenCheckValue_thenMatchesValue() {
        // Sample when is treated as a static variable
        assertTrue(CompanionSample.MAX_LIMIT == 20)
    }
}