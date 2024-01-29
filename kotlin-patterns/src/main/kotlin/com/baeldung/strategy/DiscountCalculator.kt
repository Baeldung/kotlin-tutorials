package com.baeldung.strategy

import com.baeldung.strategy.entity.Book
import com.baeldung.strategy.strategies.DiscountStrategy

class DiscountCalculator(private val discountStrategy: DiscountStrategy) {
    fun calculateDiscount(book: Book): Double {
        return discountStrategy.calculateDiscount(book)
    }
}