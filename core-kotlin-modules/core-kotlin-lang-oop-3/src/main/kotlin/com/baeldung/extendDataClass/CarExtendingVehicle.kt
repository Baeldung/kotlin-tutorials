package com.baeldung.extendDataClass

data class CarExtendingVehicle(override val age: Int, override val numberOfWheels: Int, val numberOfDoors: Int) : VehicleBase(age, numberOfWheels)
