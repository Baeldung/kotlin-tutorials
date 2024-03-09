package com.baeldung.kotlin.json

import org.json.JSONArray
import org.json.JSONObject

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
operator fun <T> JSONArray.iterator(): Iterator<T> =
    @Suppress("UNCHECKED_CAST")
    (0 until this.length()).asSequence().map { this.get(it) as T }.iterator()

class JsonArrayIteratorExtension {
    private companion object

    val BOOKS_STRING = """
            [
                {
                   "book_name":"The Hunger Games",
                   "author":"Suzanne Collins"
                },
                {
                   "book_name":"To Kill a Mockingbird ",
                   "author":"Harper Lee"
                },
                {
                   "book_name":"Pride and Prejudice",
                   "author":"Jane Austen"
                }
            ]
            """

    fun main() {
        val booksJSONArray = JSONArray(BOOKS_STRING)
        for (book in booksJSONArray) {
            println("${(book as JSONObject).get("book_name")} by ${book.get("author")}")
        }
    }
}
