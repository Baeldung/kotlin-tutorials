package com.baeldung.constructor

open class Person(
        val name: String,
        val age: Int? = null
) {
    val upperCaseName: String = name.uppercase()

    init {
        println("Hello, I'm $name")

        if (age != null && age < 0) {
            throw IllegalArgumentException("Age cannot be less than zero!")
        }
    }

    init {
        println("upperCaseName is $upperCaseName")
    }

}

fun main() {
    val person = Person("John")
    println("Person is: $person")
    val personWithAge = Person("John", 22)
    println("Person with age is: $personWithAge")
}