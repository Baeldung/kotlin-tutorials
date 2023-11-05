package com.baeldung.strategy.strategies

import com.baeldung.strategy.entity.Book

class PremiumCustomerDiscountStrategy : DiscountStrategy {
    override fun calculateDiscount(book: Book): Double {
        return book.price * 0.2
    }
}