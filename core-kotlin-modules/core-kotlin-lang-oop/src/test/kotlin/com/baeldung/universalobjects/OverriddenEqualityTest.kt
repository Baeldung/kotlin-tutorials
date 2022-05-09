package com.baeldung.universalobjects

import com.baeldung.universalObjects.OverriddenEquality
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class OverriddenEqualityTest {

    @Test
    fun areEqual() {
        val storage1 = OverriddenEquality.Storage("980Pro M.2 NVMe", 1024)
        val storage2 = OverriddenEquality.Storage("980Pro M.2 NVMe", 1024)
        val overriddenEquality = OverriddenEquality()
        Assertions.assertThat(overriddenEquality.areEqual(storage1, storage2)).isEqualTo(true)
    }
}