package com.baeldung.universalobjects

import com.baeldung.universalObjects.OverriddenEqualityStorage
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class OverriddenEqualityKtTest{
    @Test
    fun whenOverrideEquality_returnsTrue(){
        val storage1 = OverriddenEqualityStorage("980Pro M.2 NVMe", 1024)
        val storage2 = OverriddenEqualityStorage("980Pro M.2 NVMe", 1024)

        assertTrue(storage1 == storage2)
    }
}