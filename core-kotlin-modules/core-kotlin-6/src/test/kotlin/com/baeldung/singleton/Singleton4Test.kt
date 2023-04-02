package com.baeldung.singleton

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class Singleton4Test {

    @Test
    fun `When retrieving instance of singleton, Then same instance is retrieved`() {
        val instance1 = Singleton4.getInstance()
        val instance2 = Singleton4.getInstance()

        assertSame(instance1, instance2)
        assertEquals("Doing something", instance1.doSomething())
    }
}