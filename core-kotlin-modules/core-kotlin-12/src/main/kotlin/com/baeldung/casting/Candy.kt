package com.baeldung.casting

sealed class Candy(val name: String) {
    override fun toString() = name
}

object ChocolateBar: Candy("Chocolate Bar")
object Lollipop : Candy("Lollipop")