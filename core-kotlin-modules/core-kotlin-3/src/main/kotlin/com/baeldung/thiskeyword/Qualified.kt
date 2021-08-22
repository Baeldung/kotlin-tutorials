package com.baeldung.thiskeyword

class Outside {
    inner class Inside {
        fun innerInstance() = this
        fun outerInstance() = this@Outside
    }
}

fun main() {
    val outside = Outside()
    val inside = outside.Inside()

    println(inside.innerInstance()::class)
    println(inside.outerInstance()::class)
}
