package com.baeldung.json

import org.json.JSONArray

class JsonArrayForEachLoop {
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
        (0 until booksJSONArray.length()).forEach {
            val book = booksJSONArray.getJSONObject(it)
            println("${book.get("book_name")} by ${book.get("author")}")
        }
    }
}
