package com.baeldung.defaultparam

data class PersonWithAdditionalConstructor(val name: String, val age: Int) {
    constructor(name: String?, age: Int?) : this(name ?: "John", age ?: 0)
}

fun main() {
    val person = PersonWithAdditionalConstructor(null, null)
    println(person)
}
