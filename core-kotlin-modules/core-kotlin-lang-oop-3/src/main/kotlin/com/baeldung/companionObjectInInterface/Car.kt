package com.baeldung.companionObjectInInterface

class Car(val brand: String, val model: String, val age: Int) : Vehicle {

    companion object {
        const val ANTIQUE_CAR_MINIMAL_AGE = 30
    }
    override fun getNumberOfWheels() = 4
    fun isAntique() = age >= ANTIQUE_CAR_MINIMAL_AGE
}