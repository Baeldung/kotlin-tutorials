package com.baeldung.companionObjectInInterface

interface Vehicle {
    fun getNumberOfWheels(): Int

    companion object {
        const val DOUBLE_TRACK_AMOUNT_OF_WHEELS = 3
        fun isDoubleTrack(vehicle: Vehicle) = vehicle.getNumberOfWheels() > DOUBLE_TRACK_AMOUNT_OF_WHEELS
    }
}