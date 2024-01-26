package com.baeldung.visitor

class Listing(val name: String, val price: Double) {
    fun accept(visitor: ShoppingCartVisitor): Double {
        return visitor.visit(this)
    }
}