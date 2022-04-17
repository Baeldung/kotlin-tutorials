package com.baeldung.initialization

import kotlin.reflect.KProperty

class LazyInitSample {
    init {
        println("Object initialization")
    }

    val specialValue by SomeDelegate(actualValue = "I am Groot")

    val lazyValue by lazy {
        println("Only now the field is initialized")
        18
    }
}

class SomeDelegate(val actualValue: String) {
    operator fun getValue(lazyInitSample: LazyInitSample, property: KProperty<*>): String {
        println("When the property is accessed, this function is called")
        return actualValue
    }

}