package com.baeldung.comparable

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class ComparableCoercionUnitTest {

    class Version(val value: Int) : Comparable<Version> {
        override fun compareTo(other: Version): Int = value.compareTo(other.value)
    }

    @Test
    fun whenCoercedToAtLeast_thenCorrect() {
        val atLeastCoerced = Version(100).coerceAtLeast(Version(200))
        val atMostCoerced = Version(100).coerceAtMost(Version(10))
        val inRangeCoerced = Version(100).coerceIn(Version(20), Version(40))

        assertTrue(Version(100) < atLeastCoerced)
        assertTrue(Version(100) > atMostCoerced)
        assertTrue(inRangeCoerced in  Version(20)..Version(40))
    }


}