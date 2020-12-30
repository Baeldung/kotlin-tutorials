package com.baeldung.getterssetters

import java.util.logging.*

class Book {

    var author: String = "Frank Herbert"

    val title: String = "Dune"
        get() {
            return field.toUpperCase()
        }

    var rating: Int = 5
        get() {
            if (field < 5) {
                println("Warning: accessing a terrible book")
            }
            return field
        }
        set(value) {
            field = when {
                value > 10 -> 10
                value < 0 -> 0
                else -> value
            }
        }

    val isWorthReading get() = this.rating > 5

    var inventory: Int = 0
        private set
}

fun main() {
    val book = Book()
    println(book.author) // prints "Frank Herbert"
    book.author = "Kurt Vonnegut"
    println(book.author) // prints "Kurt Vonnegut"

    val logger = Logger.getGlobal()
    logger.level = Level.SEVERE // equivalent to calling setLevel(Level.SEVERE)
    println(logger.level) // prints "SEVERE"
}