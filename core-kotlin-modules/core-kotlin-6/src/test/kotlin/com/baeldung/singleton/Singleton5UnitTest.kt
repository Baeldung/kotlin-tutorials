package com.baeldung.singleton

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Singleton5UnitTest {

    @Test
    fun `When retrieving instance of singleton, Then same instance is retrieved`() {
        val instance1 = Singleton5.INSTANCE
        val instance2 = Singleton5.INSTANCE

        Assertions.assertSame(instance1, instance2)
        Assertions.assertEquals("Doing something", instance1.doSomething())
    }
}