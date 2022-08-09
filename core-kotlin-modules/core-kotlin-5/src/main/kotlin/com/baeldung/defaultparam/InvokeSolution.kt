package com.baeldung.defaultparam

data class PersonWithInvoke private constructor(val name: String, val age: Int) {
    companion object {
        operator fun invoke(name: String?, age: Int?) = PersonWithInvoke(name ?: "John", age ?: 0)
    }
}

fun main() {
    val person = PersonWithInvoke(null, null)
    println(person)
}
