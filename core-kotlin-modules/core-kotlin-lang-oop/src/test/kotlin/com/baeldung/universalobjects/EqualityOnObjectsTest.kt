package com.baeldung.universalobjects

import com.baeldung.universalObjects.EqualityOnObjects
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class EqualityOnObjectsTest {

    @Test
    fun areEqual() {
        val storage1 = EqualityOnObjects.Storage("980Pro M.2 NVMe", 1024)
        val storage2 = EqualityOnObjects.Storage("980Pro M.2 NVMe", 1024)
        val equalityOnObject = EqualityOnObjects()
        Assertions.assertThat(equalityOnObject.areEqual(storage1, storage2)).isEqualTo(false)
    }
}