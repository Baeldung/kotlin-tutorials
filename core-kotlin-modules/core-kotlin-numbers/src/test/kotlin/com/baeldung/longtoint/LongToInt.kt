package com.baeldung.longtoint

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LongToIntTest {

    @Test
    private fun `when toInt called on long result is an integer`() {
        val longValue = 100L
        val intValue = longValue.toInt()
        assertThat(100).isEqualTo(intValue)
    }
}