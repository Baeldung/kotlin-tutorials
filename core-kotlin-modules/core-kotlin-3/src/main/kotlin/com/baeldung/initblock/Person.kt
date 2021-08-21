package com.baeldung.initblock

class Person(val firstName: String, val lastName: String) {

    private val fullName: String = "$firstName $lastName".trim()
        .also { println("Initializing full name") }

    init {
        println("You're $fullName")
    }

    private val initials = "${firstName.firstOrEmpty()}${lastName.firstOrEmpty()}".trim()
        .also { println("Initializing initials") }

    init {
        println("You're initials are $initials")
    }

    constructor(lastName: String) : this("", lastName) {
        println("I'm secondary")
    }

    private fun String.firstOrEmpty(): Char = firstOrNull()?.toUpperCase() ?: ' '
}

fun main() {
    val p = Person("dehghani")
}
