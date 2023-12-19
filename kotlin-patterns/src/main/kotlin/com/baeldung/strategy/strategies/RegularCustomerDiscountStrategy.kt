package com.baeldung.strategy.strategies

import com.baeldung.strategy.entity.Book

class RegularCustomerDiscountStrategy : DiscountStrategy {
    override fun calculateDiscount(book: Book): Double {
        return book.price * 0.1
    }
}