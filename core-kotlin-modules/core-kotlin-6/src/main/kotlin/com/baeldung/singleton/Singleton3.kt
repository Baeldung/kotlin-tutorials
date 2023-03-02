package com.baeldung.singleton

class Singleton3 private constructor() {

    companion object {
        val instance:Singleton3 by lazy {
            Singleton3()
        }
    }

    fun doSomething() = "Doing something"
}
