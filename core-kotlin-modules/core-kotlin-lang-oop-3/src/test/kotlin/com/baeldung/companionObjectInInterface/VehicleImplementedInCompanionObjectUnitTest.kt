package com.baeldung.companionObjectInInterface

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

internal class VehicleImplementedInCompanionObjectUnitTest{

    @Test
    fun `given type implementing Vehicle, when use should work`(){
        val car = Car("Ford", "Mustang", 12)
        assertThat(car.getNumberOfWheels()).isEqualTo(4)
        assertThat(Vehicle.isDoubleTrack(car)).isTrue
    }

    @Test
    fun `given companion object implementing Vehicle, when use should work`(){
        val bicycle = VehicleImplementedInCompanionObject.Bicycle
        assertThat(bicycle.getNumberOfWheels()).isEqualTo(2)
        assertThat(Vehicle.isDoubleTrack(bicycle)).isFalse
    }
}