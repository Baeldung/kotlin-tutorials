package com.baeldung.defaultparam


data class Person(val name: String, val age: Int)

fun main() {
    val nullableName: String? = "John"
    val nullableAge: Int? = null

    // Does not compile, because person does not accept nulls
    //    Person(
    //        name = nullableName,
    //        age = nullableAge
    //    )

    // Compiles, because we inserted defaults for nullableName and nullableAge. It's ugly though.
    Person(
        name = nullableName ?: "John",
        age = nullableAge ?: 0
    )
}
