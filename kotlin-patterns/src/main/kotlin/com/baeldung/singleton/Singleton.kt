package com.baeldung.singleton

object Singleton {
    init {
        println("Singleton class invoked.")
    }

    const val name = "SingletonClass"

    fun add(num1: Int, num2: Int): Int {
        return num1.plus(num2)
    }
}