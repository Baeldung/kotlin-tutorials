package com.baeldung.visitor

interface ShoppingCartVisitor {
    fun visit(listing: Listing): Double
    fun visit(cart: Cart): Double
}