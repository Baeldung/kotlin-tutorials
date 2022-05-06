package com.baeldung.universalobjects

import com.baeldung.universalObjects.OverriddenEqualityAndHashCodeStorage
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class OverriddenHashCodeKtTest{
    @Test
    fun whenOverrideHashCodeAndEquals_returnsTrue(){
        val storage1 = OverriddenEqualityAndHashCodeStorage("980Pro M.2 NVMe", 1024)
        val storage2 = OverriddenEqualityAndHashCodeStorage("980Pro M.2 NVMe", 1024)

        assertTrue(storage1 == storage2)
    }
}