package com.baeldung.range

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OtherRangeFunctionsUnitTest {

    val r = 1..20
    val repeated = listOf(1, 1, 2, 4, 4, 6, 10)

    @Test
    fun testMin() {
        assertEquals(1, r.minOrNull())
    }

    @Test
    fun testMax() {
        assertEquals(20, r.maxOrNull())
    }

    @Test
    fun testSum() {
        assertEquals(210, r.sum())
    }

    @Test
    fun testAverage() {
        assertEquals(10.5, r.average())
    }

    @Test
    fun testCount() {
        assertEquals(20, r.count())
    }

    @Test
    fun testDistinct() {
        assertEquals(listOf(1, 2, 4, 6, 10), repeated.distinct())
    }
}