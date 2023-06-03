package com.baeldung.comparable

import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertTrue

class NaturalOrderWithDelegateUnitTest {

    class Version(val value: Int, val timestamp: Instant) : Comparable<Version> by VersionCriteriaComparable(value, timestamp)

    class VersionCriteriaComparable(private val value: Int, private val timestamp: Instant): Comparable<Version> {
    override fun compareTo(other: Version): Int = when (
        val valueComparison = value.compareTo(other.value)) {
            0 -> timestamp compareTo other.timestamp
            else -> valueComparison
        }
    }

    @Test
    fun whenVersionIsMoreRecent_thenCorrect() {
        assertTrue(Version(0, Instant.EPOCH) < Version(0, Instant.now()))
    }

}