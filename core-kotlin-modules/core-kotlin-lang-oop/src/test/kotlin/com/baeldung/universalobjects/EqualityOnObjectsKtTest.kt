package com.baeldung.universalobjects

import com.baeldung.universalObjects.Storage
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class EqualityOnObjectsKtTest{
    @Test
    fun whenTwoObjectsHaveSameValue_returnsFalse(){
        var storage1 = Storage("980Pro M.2 NVMed", 1024)
        var storage2 = Storage("980Pro M.2 NVMe", 1024)

        assertFalse(storage1 == storage2)
    }
}