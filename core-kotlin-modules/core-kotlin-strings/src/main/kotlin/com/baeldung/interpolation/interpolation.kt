package com.baeldung.interpolation

class Person(val firstName: String, val lastName: String, val age: Int) {

    override fun toString(): String {
        return "$firstName $lastName is $age years old"
    }
}
