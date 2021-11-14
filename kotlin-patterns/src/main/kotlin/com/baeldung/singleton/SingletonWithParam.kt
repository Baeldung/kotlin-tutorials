package com.baeldung.singleton

class SingletonWithParam private constructor(str: String) {
    var paramValue: String = str
        private set

    companion object {
        @Volatile
        var instance: SingletonWithParam? = null

        fun getInstance(c: String): SingletonWithParam =
            instance ?: synchronized(this) {
                instance ?: SingletonWithParam(c).also { instance = it }
            }
    }
}