package com.baeldung.iterate.components

data class Person(val name: String, val age: Int) : Employee() {
    val isAdult: Boolean
        get() = age >= 18

    val Person.isTeenager: Boolean
        get() = age in 13..19

    fun Person.isRetired(): Boolean {
        return age >= 65
    }

    companion object Create {
        fun create(name: String, age: Int) = Person(name, age)
    }

    data class Job(val title: String, val salary: Float)

    object Address {
        const val planet: String = "Earth"
    }
}

fun Person.components(): Iterator<Pair<String, Any>> {
    return listOf(
        "name" to name,
        "age" to age,
        "isAdult" to isAdult,
        "isTeenager" to isTeenager,
        "isRetired" to isRetired()
    ).iterator()
}
