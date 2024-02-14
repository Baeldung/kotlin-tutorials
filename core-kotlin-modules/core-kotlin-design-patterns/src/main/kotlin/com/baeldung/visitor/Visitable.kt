package com.baeldung.visitor

interface Visitable {
    fun accept(visitor: ShoppingCartVisitor): Double
}