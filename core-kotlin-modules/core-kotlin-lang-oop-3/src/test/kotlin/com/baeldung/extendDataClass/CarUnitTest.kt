package com.baeldung.extendDataClass

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CarUnitTest {

    @Test
    fun `given a Car object when compare with equals should get true`() {
        val fordFocus = Car(age = 2, numberOfWheels = 4, numberOfDoors = 4)
        val fordFocusClone = Car(age = 2, numberOfWheels = 4, numberOfDoors = 4)
        assertThat(fordFocus).isEqualTo(fordFocusClone)
    }
}