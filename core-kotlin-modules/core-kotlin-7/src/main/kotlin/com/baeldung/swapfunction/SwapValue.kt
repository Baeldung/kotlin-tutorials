package com.baeldung.swapfunction

fun <T> swapValues(a: T, b: T): Pair<T, T> {
    return Pair(b, a)
}


class SwapValue {
    var x = 10
    var y = 20

    fun SwapValues(){
        println("Before swap: x = $x, y = $y")
        val swappedValues = swap(x, y)
        x = swappedValues.first
        y = swappedValues.second
        println("After swap: x = $x, y = $y")
    }
}


