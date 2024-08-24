package com.baeldung.nullable.comparable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NullableComparableUnitTest {
    @Test
    fun `compare nullable integers using compareTo`() {
        val a: Int? = 5
        val b: Int? = 3
        val c: Int? = null
        assertEquals(0, compareTwoInts(a, a))
        assertEquals(1, compareTwoInts(a, b))
        assertEquals(-1, compareTwoInts(b, a))
        assertEquals(null, compareTwoInts(a, c))
        assertEquals(null, compareTwoInts(c, b))
    }

    @Test fun `compare nullable integers using extension function`() {
        val a: Int? = 5
        val b: Int? = 3
        val c: Int? = null
        assertEquals(true, a.isGreaterThan(b))
        assertEquals(false, b.isGreaterThan(a))
        assertEquals(null, c.isGreaterThan(a))
        assertEquals(null, c.isGreaterThan(c))
    }

    @Test fun `compare generic Int comparable types using extension function`() {
        val a: Int? = 5
        val b: Int? = 3
        val c: Int? = null
        assertEquals(true, a.isGreaterThan(b))
        assertEquals(false, b.isGreaterThan(a))
        assertEquals(false, b.isGreaterThan(b))
        assertEquals(null, c.isGreaterThan(b))
    }

    @Test fun `compare generic String comparable types using extension function`() {
        val a: String? = "ABC"
        val b: String? = "ZXY"
        val c: String? = null
        assertEquals(true, b.isGreaterThan(a))
        assertEquals(false, a.isGreaterThan(b))
        assertEquals(false, b.isGreaterThan(b))
        assertEquals(null, c.isGreaterThan(b))
    }

    @Test fun `compare nullable integers using compareValues function`() {
        val a: Int? = 5
        val b: Int? = 3
        val c: Int? = null
        assertEquals(1, compareValues(a, b))
        assertEquals(-1, compareValues(b, a))
        assertEquals(1, compareValues(a, c))
        assertEquals(-1, compareValues(c, a))
        assertEquals(0, compareValues(c, c)) }
}