package com.baeldung.extendDataClass

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CarExtendingVehicleUnitTest {

    @Test
    fun `given a carExtendingVehicle object when compare with equals should get true`() {
        val fordMustang = CarExtendingVehicle(age = 10, numberOfWheels = 2, numberOfDoors = 4)
        val fordMustangClone = CarExtendingVehicle(age = 10, numberOfWheels = 2, numberOfDoors = 4)
        assertThat(fordMustang).isEqualTo(fordMustangClone)
    }
}