package com.baeldung.visitor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VisitorPatternUnitTest {

    @Test
    fun `test shopping cart visitor`() {
        val cart = Cart()
        cart.addListing(Listing("Listing 1", 10.0))
        cart.addListing(Listing("Listing 2", 20.0))
        cart.addListing(Listing("Listing 3", 30.0))

        val visitor = ShoppingCartVisitorImpl()
        val totalPrice = cart.accept(visitor)

        assertEquals(60.0, totalPrice)

        cart.removeListing(Listing("Listing 2", 20.0))
    }
}