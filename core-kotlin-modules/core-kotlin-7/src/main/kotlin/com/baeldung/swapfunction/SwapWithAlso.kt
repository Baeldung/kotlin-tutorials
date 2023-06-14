package com.baeldung.swapfunction


class SwapWithAlso{

    var x = 10
    var y = 20

    fun main() {
        println("Before swap: x = $x, y = $y")
        val swappedValues = Pair(y, x).also { (a, b) ->
            x = a
            y = b
            println("After swap: x = $x, y = $y")
        }
    }
}