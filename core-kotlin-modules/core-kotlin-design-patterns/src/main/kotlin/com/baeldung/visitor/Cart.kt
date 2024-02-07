package com.baeldung.visitor

class Cart: Visitable {
    val listings = mutableListOf<Listing>()

    fun addListing(listing: Listing) {
        listings.add(listing)
    }

    fun removeListing(listing: Listing) {
        listings.remove(listing)
    }

    override fun accept(visitor: ShoppingCartVisitor): Double {
        return visitor.visit(this)
    }
}