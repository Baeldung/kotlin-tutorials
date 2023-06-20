package com.baeldung.swapfunction

class SwapValue {

    var a = 1
    var b = 2
    fun swapValues(){
        println("Before swap: a => $a; b => $b")
        val temp = a
        a = b
        b = temp
        println("After swap: a => $a; b => $b")
    }
}


