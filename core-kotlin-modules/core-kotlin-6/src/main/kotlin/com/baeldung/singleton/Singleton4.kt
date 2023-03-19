package com.baeldung.singleton

class Singleton4 private constructor() {
    companion object {
        @Volatile
        private var instance: Singleton4? = null

        fun getInstance(): Singleton4 {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Singleton4()
                    }
                }
            }
            return instance!!
        }
    }

    fun doSomething() = "Doing something"
}