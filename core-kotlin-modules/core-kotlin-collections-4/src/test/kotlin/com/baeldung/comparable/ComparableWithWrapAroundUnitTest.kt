package com.baeldung.comparable

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ComparableWithWrapAroundUnitTest {

    private class BankAccount(private val balance: Int): Comparable<BankAccount> {
        override fun compareTo(other: BankAccount): Int = (balance - other.balance)
    }

    @Test
    fun whenComparingWithinCriteriaTypeLimits_thenCorrect() {
        assertTrue(BankAccount(1900) > BankAccount(20))
        assertTrue(BankAccount(100) <= BankAccount(200))
    }

    @Test
    fun whenComparingOutsideCriteriaTypeLimits_thenFailure() {
        assertFalse(BankAccount(Int.MAX_VALUE) > BankAccount(-100))
    }
}