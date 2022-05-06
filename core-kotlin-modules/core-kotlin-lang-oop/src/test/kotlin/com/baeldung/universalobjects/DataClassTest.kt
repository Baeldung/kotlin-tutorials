package com.baeldung.universalobjects

import com.baeldung.universalObjects.DataClass
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class DataClassTest {

    @Test
    fun areEqual() {
        val storage1 = DataClass.Storage("980Pro M.2 NVMe", 1024)
        val storage2 = DataClass.Storage("980Pro M.2 NVMe", 1024)
        val dataClass = DataClass()
        Assertions.assertThat(dataClass.areEqual(storage1, storage2)).isEqualTo(true)
    }
}