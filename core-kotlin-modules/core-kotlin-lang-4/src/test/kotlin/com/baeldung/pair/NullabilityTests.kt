package com.baeldung.pair

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class NullabilityTests {
    @Test
    fun Test(){
        val name: String? = null
        assertThatThrownBy {
            name!!.length
        }.isInstanceOf(NullPointerException::class.java)
    }
}
