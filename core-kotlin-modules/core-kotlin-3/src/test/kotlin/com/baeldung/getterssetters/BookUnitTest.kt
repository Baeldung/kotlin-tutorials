package com.baeldung.getterssetters

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BookUnitTest {

    @Test
    fun `Given a Book instance, When author is modified, Then author property is updated`() {
        val book = Book()

        assertEquals(DEFAULT_AUTHOR, book.author)
        book.author = "Kurt Vonnegut"
        assertEquals("Kurt Vonnegut", book.author)
    }

    @Test
    fun `Given a Book instance, When title property is read, Then custom getter is called`() {
        val book = Book()

        assertEquals(DEFAULT_TITLE.toUpperCase(), book.title)
    }


    @Test
    fun `Given a Book instance, When rating property is modified, Then custom setter is called`() {
        val book = Book()

        // Values greater than 10 are set to 10
        book.rating = 11
        assertEquals(10, book.rating)

        // Values less than 0 are set to 0
        book.rating = -1
        assertEquals(0, book.rating)

        // Values between 0 and 10 are set as given
        book.rating = 7
        assertEquals(7, book.rating)
    }

    @Test
    fun `Given a Book instance, When rating property is modified, Then isWorthReading property is updated`() {
        val book = Book()

        // Default rating is 5
        assertFalse(book.isWorthReading)

        book.rating = 6
        assertTrue(book.isWorthReading)
    }

    @Test
    fun `Given a Book instance, When inventory property has private setter, Then property is still readable publicly`() {
        val book = Book()

        assertEquals(0, book.inventory)
    }
}