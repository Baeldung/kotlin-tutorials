package com.baeldung.booleanToInt

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


fun Boolean.toInt() = if (this) 1 else 0

val Boolean.intValue
    get() = if (this) 1 else 0

class BooleanToIntUnitTest {

    @Test
    fun `given two booleans, when comparing them, should get expected result`() {
        assertThat(true > false).isTrue
    }

    @Test
    fun `given a Boolean, when calling toInt, should get expected result`() {
        assertThat(true.toInt()).isEqualTo(1)
        assertThat(false.toInt()).isEqualTo(0)
    }

    @Test
    fun `given a Boolean, when get intValue, should get expected result`() {
        assertThat(true.intValue).isEqualTo(1)
        assertThat(false.intValue).isEqualTo(0)
    }

    @Test
    fun `given a Boolean, when calling compareTo false, should get expected result`() {
        assertThat(true.compareTo(false)).isEqualTo(1)
        assertThat(false.compareTo(false)).isEqualTo(0)

        assertThat(true.compareTo(true)).isEqualTo(0)
        assertThat(false.compareTo(true)).isEqualTo(-1)
    }

}