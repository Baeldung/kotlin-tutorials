package com.baeldung.jvmstatic

class Math {
    companion object {
        @JvmStatic
        fun abs(x: Int) = if (x < 0) -x else x
    }
}

fun main() {
    println(Math.abs(-2))
}