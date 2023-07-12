package com.baeldung.swapfunction.classes


import org.junit.Assert.assertEquals
import org.junit.Test

class SwapValue {
    @Test
    fun swapValues(){
        var a = 1
        var b = 2
        assertEquals(1, a)
        assertEquals(2, b)
        val temp = a
        a = b
        b = temp
        assertEquals(2, a)
        assertEquals(1, b)
    }
}


