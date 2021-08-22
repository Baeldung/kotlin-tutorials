package com.baeldung.thiskeyword

class Car(val id: String, val type: String) {
    constructor(id: String) : this(id, "unknown")
}

fun main() {
    val car = Car("001")
    println("car id = ${car.id}")
}
