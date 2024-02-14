package com.baeldung.annotations

fun main() {
    val item = Item(amount = 1.0f, name = "Bob")
    val validator = Validator()
    println("Is instance valid? ${validator.isValid(item)}")
}
