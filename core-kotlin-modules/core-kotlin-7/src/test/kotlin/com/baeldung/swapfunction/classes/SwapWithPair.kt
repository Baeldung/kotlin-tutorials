package com.baeldung.swapfunction.classes


import junit.framework.TestCase.assertEquals

fun <T> swap(a: T, b: T): Pair<T, T> {
    return Pair(b, a)
}

class SwapWithPair {
    var x = 100
    var y = 200

    fun swapUsingDestructuring(){
        assertEquals(100, x)
        assertEquals(200, y)
        val result = swap(x, y)
        x = result.first
        y = result.second
        assertEquals(200, x)
        assertEquals(100, y)
    }
}