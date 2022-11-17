package com.baeldung.companionObjectInInterface

class VehicleImplementedInCompanionObject {

    companion object Bicycle : Vehicle {
        override fun getNumberOfWheels(): Int {
            return 2
        }
    }
}