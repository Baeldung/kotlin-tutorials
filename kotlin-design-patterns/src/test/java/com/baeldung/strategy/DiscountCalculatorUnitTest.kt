package com.baeldung.strategy

import com.baeldung.strategy.entity.Book
import com.baeldung.strategy.entity.Customer
import com.baeldung.strategy.entity.MembershipType
import com.baeldung.strategy.strategies.PremiumCustomerDiscountStrategy
import com.baeldung.strategy.strategies.RegularCustomerDiscountStrategy
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DiscountCalculatorUnitTest {
    @Test
    fun `calculate discount for regular customer`() {
        val book = Book("Effective Java", 100.0)
        val customer = Customer("John Doe", MembershipType.REGULAR)
        val discountCalculator = createDiscountCalculator(customer)
        val discount = discountCalculator.calculateDiscount(book)
        assertEquals(10.0, discount)
    }

    @Test
    fun `calculate discount for premium customer`() {
        val book = Book("Effective Java", 100.0)
        val customer = Customer("John Doe", MembershipType.PREMIUM)
        val discountCalculator = createDiscountCalculator(customer)
        val discount = discountCalculator.calculateDiscount(book)
        assertEquals(20.0, discount)
    }

    private fun createDiscountCalculator(customer: Customer): DiscountCalculator {
        val discountStrategy = when (customer.membershipType) {
            MembershipType.REGULAR -> RegularCustomerDiscountStrategy()
            MembershipType.PREMIUM -> PremiumCustomerDiscountStrategy()
        }

        return DiscountCalculator(discountStrategy);
    }
}