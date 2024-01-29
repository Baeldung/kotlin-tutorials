package com.baeldung.strategy.strategies

import com.baeldung.strategy.entity.Book

interface DiscountStrategy {
    fun calculateDiscount(book: Book): Double
}