package com.baeldung.comparable

import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertTrue

typealias Selector<T> = (T)-> Comparable<*>

class NaturalOrderWithGeneralPurposeDelegateUnitTest {

    class Version(val value: Int, val timestamp: Instant) : Comparable<Version> by DelegatedComparable(
        value to Version::value,
        timestamp to Version::timestamp
    )

    class DelegatedComparable<T>(vararg criteria: Pair<Comparable<*>, Selector<T>>): Comparable<T> {

        @Suppress("UNCHECKED_CAST")
        private val comparator: Comparator<T> = criteria.fold(Comparator { _, _ -> 0 }) { acc, crit ->
            acc.then { _, b ->
                (crit.first as Comparable<Any>).compareTo(crit.second(b))
            }
        }
        override fun compareTo(other: T): Int = comparator.compare(null, other)
    }

    @Test
    fun whenVersionIsMoreRecent_thenCorrect() {
        assertTrue(Version(0, Instant.EPOCH) < Version(0, Instant.now()))
    }

    @Test
    fun whenValueIsGreater_thenCorrect() {
        assertTrue(Version(0, Instant.EPOCH) < Version(1, Instant.now()))
    }

    @Test
    fun whenValueIsEqual_thenCorrect() {
        assertTrue(Version(1, Instant.EPOCH).compareTo(Version(1, Instant.EPOCH)) == 0)
    }

}