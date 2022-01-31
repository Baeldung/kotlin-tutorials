package com.baeldung.initialization

class LateinitSample {
    lateinit var lateValue: ValueType

    fun initBasedOnEnvironment(env: Map<String, String>) {
        lateValue = ValueType(env.toString())
    }
}

data class ValueType(val someValue: String)