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

    // Compiles, because we inserted defaults for nullableName and nullableAge.
    //  The issue is that we have to remember to provide defaults each time.
    Person(
        name = nullableName ?: "John",
        age = nullableAge ?: 0
    )
}
