package com.baeldung.extendDataClass

//data class Car(override val age: Int, override val numberOfWheels: Int, val numberOfDoors: Int) : Vehicle(age, numberOfWheels)
data class Car(override val age: Int, override val numberOfWheels: Int, val numberOfDoors: Int) : IVehicle