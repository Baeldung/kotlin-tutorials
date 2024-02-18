package com.baeldung.range

fun main() {

    for(i in 1..9 step 2){
        print(i)
    }

    println()

    for (i in 9 downTo 1 step 2){
        print(i)
    }

}