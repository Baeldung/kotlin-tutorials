package com.baeldung.swapfunction.classes


import org.junit.Assert.assertEquals
import org.junit.Test

fun <T> swap(a: T, b: T): Pair<T, T> {
    return Pair(b, a)
}

class SwapWithPair {

    @Test
    fun swapUsingDestructuring(){
        var x = 100
        var y = 200

        assertEquals(100, x)
        assertEquals(200, y)
        val result = swap(x, y)
        x = result.first
        y = result.second
        assertEquals(200, x)
        assertEquals(100, y)
    }
}