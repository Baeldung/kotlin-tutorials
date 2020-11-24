package com.baeldung.kotlin.objects

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test

class ObjectsUnitTest {
    @Test
    fun singleton() {

        assertEquals(42, SimpleSingleton.answer)
        assertEquals("Hello, world!", SimpleSingleton.greet("world"))
    }

    @Test
    fun counter() {
        assertEquals(0, Counter.currentCount())
        Counter.increment()
        assertEquals(1, Counter.currentCount())
        Counter.decrement()
        assertEquals(0, Counter.currentCount())
    }

    @Test
    fun comparator() {
        val strings = listOf("Hello", "World")
        val sortedStrings = strings.sortedWith(ReverseStringComparator)

        assertEquals(listOf("World", "Hello"), sortedStrings)
    }

    @Test
    fun companion() {
        assertEquals("You can see me", OuterClass.public)
        // assertEquals("You can't see me", OuterClass.secret) // Cannot access 'secret'
    }
}
