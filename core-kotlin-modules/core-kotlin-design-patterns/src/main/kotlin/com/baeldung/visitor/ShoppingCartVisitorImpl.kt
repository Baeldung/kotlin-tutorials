package com.baeldung.visitor

class ShoppingCartVisitorImpl : ShoppingCartVisitor {
    override fun visit(listing: Listing): Double {
        return listing.price
    }

    override fun visit(cart: Cart): Double {
        var totalPrice = 0.0
        for (listing in cart.listings) {
            totalPrice += listing.accept(this)
        }
        return totalPrice
    }
}