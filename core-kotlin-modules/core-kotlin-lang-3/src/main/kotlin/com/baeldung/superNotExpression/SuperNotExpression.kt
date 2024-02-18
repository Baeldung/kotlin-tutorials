package com.baeldung.superNotExpression

open class Vehicle() {
    constructor(name: String) : this() {
        println("Constructor parameter: $name")
    }

    open fun start() {}
}

class Motorbike : Vehicle {
    constructor(name: String) : super(name) {}

    override fun start() {
        super.start()
    }
}

class Car() : Vehicle() {
    init {
        /*
        super() //compilation error
         */
    }
}
