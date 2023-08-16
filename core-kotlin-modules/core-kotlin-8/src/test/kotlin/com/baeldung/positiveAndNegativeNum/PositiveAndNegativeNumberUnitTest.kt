package com.baeldung.positiveAndNegativeNum

import com.baeldung.positiveAndNegativeNum.NumberCategory.Negative
import com.baeldung.positiveAndNegativeNum.NumberCategory.Positive
import com.baeldung.positiveAndNegativeNum.NumberCategory.Zero
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

enum class NumberCategory {
    Positive, Negative, Zero
}

fun categoryOfInt1(n: Int): NumberCategory {
    return if (n > 0) {
        Positive
    } else if (n < 0) {
        Negative
    } else {
        Zero
    }
}

fun categoryOfInt2(n: Int): NumberCategory {
    return when {
        n > 0 -> Positive
        n < 0 -> Negative
        else -> Zero
    }
}

fun categoryOfNum(n: Number): NumberCategory {
    val d = n.toDouble()
    return when {
        d > 0.0 -> Positive
        d < 0.0 -> Negative
        else -> Zero
    }
}

fun Number.category(): NumberCategory {
    val d = toDouble()
    return when {
        d > 0.0 -> Positive
        d < 0.0 -> Negative
        else -> Zero
    }
}

class PositiveAndNegativeNumberUnitTest {
    @Test
    fun `when using categoryOfInt1 on Int then get expected result`() {
        assertEquals(Positive, categoryOfInt1(42))
        assertEquals(Negative, categoryOfInt1(-42))
        assertEquals(Zero, categoryOfInt1(0))
    }

    @Test
    fun `when using categoryOfInt2 on Int then get expected result`() {
        assertEquals(Positive, categoryOfInt2(42))
        assertEquals(Negative, categoryOfInt2(-42))
        assertEquals(Zero, categoryOfInt2(0))
    }

    @Test
    fun `when using categoryOfNum on Numbers then get expected result`() {
        //Int
        assertEquals(Positive, categoryOfNum(42))
        assertEquals(Negative, categoryOfNum(-42))
        assertEquals(Zero, categoryOfNum(0))

        //Long
        assertEquals(Positive, categoryOfNum(42L))
        assertEquals(Negative, categoryOfNum(-42L))
        assertEquals(Zero, categoryOfNum(0L))

        //Double
        assertEquals(Positive, categoryOfNum(42.42))
        assertEquals(Negative, categoryOfNum(-42.42))
        assertEquals(Zero, categoryOfNum(0.00))

        //Float
        assertEquals(Positive, categoryOfNum(42.42f))
        assertEquals(Negative, categoryOfNum(-42.42f))
        assertEquals(Zero, categoryOfNum(0.00f))

    }

    @Test
    fun `when using extension function on Numbers then get expected result`() {
        //Int
        assertEquals(Positive, 42.category())
        assertEquals(Negative, (-42).category())
        assertEquals(Zero, 0.category())

        //Long
        assertEquals(Positive, 42L.category())
        assertEquals(Negative, (-42L).category())
        assertEquals(Zero, 0L.category())

        //Double
        assertEquals(Positive, categoryOfNum(42.42))
        assertEquals(Negative, categoryOfNum(-42.42))
        assertEquals(Zero, categoryOfNum(0.00))

        //Float
        assertEquals(Positive, 42.42f.category())
        assertEquals(Negative, (-42.42f).category())
        assertEquals(Zero, 0.00f.category())
    }
}