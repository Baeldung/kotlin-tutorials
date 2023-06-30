package com.baeldung.swapfunction.classes

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals

class SwapWithAlso {
    var a = 1
    var b = 2
    fun swapWithAlso(){

        assertEquals(1, a)
        TestCase.assertEquals(2, b)
        val temp = a
        a = b
        b = temp
        assertEquals(2, a)
        assertEquals(1, b)
    }
}
