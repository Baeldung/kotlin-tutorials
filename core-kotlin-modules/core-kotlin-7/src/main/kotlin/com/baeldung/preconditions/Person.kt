package com.baeldung.preconditions

class Person(name: String?, age: Int?) {
    val name: String = requireNotNull(name) { "Name must not be null" }
    val age: Int = requireNotNull(age) { "Age must not be null" }
}