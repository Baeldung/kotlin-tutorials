package com.baeldung.swapfunction

fun <T> swap(a: T, b: T): Pair<T, T> {
    return Pair(b, a)
}

class SwapWithToOperator {
    var x = 100
    var y = 200

    fun swapUsingDestructuring(){
        println("Before swap: x = $x, y = $y")
        val result = swap(x, y)
        x = result.first
        y = result.second
        println("After swap: x = $x, y = $y")
    }
}