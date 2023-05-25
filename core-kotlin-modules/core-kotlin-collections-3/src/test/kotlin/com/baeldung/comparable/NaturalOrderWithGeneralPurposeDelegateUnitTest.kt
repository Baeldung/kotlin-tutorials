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
        private val comparator: Comparator<Any> = criteria.fold(Comparator { _: Comparable<*>, _: Comparable<*> -> 0 }) { acc, crit ->
            acc.then { _, b: Comparable<*> ->
                (crit.first as Comparable<Any>).compareTo(crit.second(b as T))
            }
        } as Comparator<Any>

        override fun compareTo(other: T): Int = comparator.compare(this, other)
    }

    @Test
    fun whenVersionIsMoreRecent_thenCorrect() {
        assertTrue(Version(0, Instant.EPOCH) < Version(0, Instant.now()))
    }

    @Test
    fun whenValueIsGreater_thenCorrect() {
        assertTrue(Version(0, Instant.EPOCH) < Version(1, Instant.now()))
    }

}