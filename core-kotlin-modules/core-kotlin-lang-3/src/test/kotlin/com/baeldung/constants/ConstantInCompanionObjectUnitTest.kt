package com.baeldung.constants


import com.baeldung.constants.ConstantsBestPractices.Companion.CONSTANT_IN_COMPANION_OBJECT
import org.assertj.core.api.Assertions
import org.junit.Test

class ConstantInCompanionObjectUnitTest {

    @Test
    fun whenAccessingConstantInCompanionObject_thenItWorks() {
        Assertions.assertThat(CONSTANT_IN_COMPANION_OBJECT).isEqualTo("constant in companion object")
    }
}