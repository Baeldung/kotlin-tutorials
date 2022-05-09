package com.baeldung.universalobjects

import com.baeldung.universalObjects.EqualityOnStrings
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class EqualityOnStringsTest {

    @Test
    fun areEqual() {
        val a = "Baeldung"
        val b = "Baeldung"
        val equalityOnStrings = EqualityOnStrings()
        Assertions.assertThat(equalityOnStrings.areEqual(a, b)).isEqualTo(true)
    }
}