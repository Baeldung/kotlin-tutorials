package com.baeldung.constructor

class Car {
    val id: String
    val type: String

    constructor(id: String, type: String) {
        this.id = id
        this.type = type
    }

}

fun main() {
    val car = Car("1", "sport")
    println("Car is: $car")
    val car1 = Car("2", "suv")
    println("Second car is: $car1")
}