package com.baeldung.dataclasses

data class Person(val age: Int, val firstName: String, val lastName: String) {
    constructor() : this(0, "Jonny", "Doe")
}

data class Car(
  val registrationPlate: String,
  val owner: Person
)