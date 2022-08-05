package com.baeldung.defaultparam

data class PersonWithGetter(private val nullableName: String?, private val nullableAge: Int?) {
    val name: String
        get() = nullableName ?: ""

    val age: Int
        get() = nullableAge ?: 0
}

fun main() {
    val person = PersonWithGetter(null, null)
    println(person)
}

