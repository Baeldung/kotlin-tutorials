package com.baeldung.universalobjects

import com.baeldung.universalObjects.OverriddenHashCode
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class OverriddenHashCodeTest {

    @Test
    fun areEqual() {
        val storage1 = OverriddenHashCode.Storage("980Pro M.2 NVMe", 1024)
        val storage2 = OverriddenHashCode.Storage("980Pro M.2 NVMe", 1024)
        val overriddenHashCode = OverriddenHashCode()
        Assertions.assertThat(overriddenHashCode.areEqual(storage1, storage2)).isEqualTo(true)
    }
}