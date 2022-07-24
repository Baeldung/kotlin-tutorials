package com.baeldung.defaultparam


class PersonClassSolution(nullableName: String?, nullableAge: Int?) {
    val name: String = nullableName ?: "John"
    val age: Int = nullableAge ?: 30
}

fun main() {
    val person = PersonClassSolution("Frederic", nullableAge = null)
    println("Person name: ${person.name}")
    println("Person age: ${person.age}")
}
