package com.baeldung.initialization

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LazyInitSampleUnitTest {
    @Test
    fun given_lazy_init_when_accessed_then_and_only_then_initialized() {
        val sample = LazyInitSample()
        assertEquals(18, sample.lazyValue)
    }
}