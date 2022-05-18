package com.baeldung.universalobjects

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class ObjectComparisonTest {

    @Test
    fun compareSimpleObjects() {
        val storage1 = ObjectComparison.Storage("980Pro M.2 NVMe", 1024)
        val storage2 = ObjectComparison.Storage("980Pro M.2 NVMe", 1024)
        assertFalse(storage1 == storage2)
    }

    @Test
    fun compareOverriddenEqualsAndHashCode() {
        val storage1 = ObjectComparison.StorageOverriddenEqualsAndHashCode("980Pro M.2 NVMe", 1024)
        val storage2 = ObjectComparison.StorageOverriddenEqualsAndHashCode("980Pro M.2 NVMe", 1024)
        assertTrue(storage1 == storage2)
    }

    @Test
    fun compareDataClasses() {
        val storage1 = ObjectComparison.StorageDataClass("980Pro M.2 NVMe", 1024)
        val storage2 = ObjectComparison.StorageDataClass("980Pro M.2 NVMe", 1024)
        assertTrue(storage1 == storage2)
    }
}