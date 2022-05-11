package com.baeldung.universalobjects

import com.baeldung.universalObjects.DataClassStorage
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class DataClassKtTest{
    @Test
    fun whenUseDataClass_returnsTrue(){
        val storage1 = DataClassStorage("980Pro M.2 NVMe", 1024)
        val storage2 = DataClassStorage("980Pro M.2 NVMe", 1024)

        assertTrue(storage1 == storage2)
    }
}