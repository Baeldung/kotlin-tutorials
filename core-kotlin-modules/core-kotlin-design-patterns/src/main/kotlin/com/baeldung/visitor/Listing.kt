package com.baeldung.visitor

class Listing(val name: String, val price: Double): Visitable {
    override fun accept(visitor: ShoppingCartVisitor): Double {
        return visitor.visit(this)
    }
}