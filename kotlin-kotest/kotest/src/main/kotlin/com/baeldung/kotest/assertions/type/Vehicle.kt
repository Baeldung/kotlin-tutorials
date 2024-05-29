package com.baeldung.kotest.assertions.type

open class Vehicle(open val speed: Int, open val wheels: Int)

class Car(speed: Int) : Vehicle(speed, 4)
class Motorcycle(speed: Int) : Vehicle(speed, 2)