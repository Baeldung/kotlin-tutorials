package com.baeldung.initialization

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class LateinitSampleUnitTest {

    @Test
    fun given_lateinit_value_is_not_initialized_when_accessed_then_exception_thrown() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            val sample = LateinitSample()
            sample.lateValue
        }
    }

    @Test
    fun given_lateinit_value_is_initialized_when_accessed_then_everything_is_fine() {
        val sample = LateinitSample().apply {
            initBasedOnEnvironment(mapOf("key" to "value"))
        }
        assertNotNull(sample.lateValue)

    }
}