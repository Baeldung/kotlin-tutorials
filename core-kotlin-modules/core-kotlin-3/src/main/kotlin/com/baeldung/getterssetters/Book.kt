package com.baeldung.getterssetters

const val DEFAULT_AUTHOR = "Frank Herbert"
const val DEFAULT_TITLE = "Dune"

class Book {

    var author: String = DEFAULT_AUTHOR

    val title: String = DEFAULT_TITLE
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