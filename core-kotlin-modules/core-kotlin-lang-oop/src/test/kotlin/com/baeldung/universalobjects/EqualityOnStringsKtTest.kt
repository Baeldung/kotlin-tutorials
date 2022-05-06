package com.baeldung.universalobjects

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class EqualityOnStringsKtTest{
    @Test
    fun whenTwoStringsAreEqual_returnTrue(){
        val a = "Baeldung"
        val b = "Baeldung"
        assertTrue(a == b)
    }
}