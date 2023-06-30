package com.baeldung.swapfunction.classes

import junit.framework.TestCase.assertEquals

class SwapValue {

    var a = 1
    var b = 2
    fun swapValues(){
        assertEquals(1, a)
        assertEquals(2, b)
        val temp = a
        a = b
        b = temp
        assertEquals(2, a)
        assertEquals(1, b)
    }
}


