package com.baeldung.swapfunction

class SwapWithAlso {
    var a = 1
    var b = 2
    fun swapWithAlso(){

        println("Before swap: a => $a; b => $b")
        val temp = a
        a = b
        b = temp
        println("After swap: a => $a; b => $b")
    }
}
