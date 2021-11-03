package com.baeldung.singleton

import org.junit.Assert
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

class SingletonTest {
    @Test
    fun `Get singleton object id and assert equals`() {
        val objectId1 = Singleton.toString()
        val objectId2 = Singleton.toString()

        Assert.assertEquals(objectId1, objectId2)
    }

    @Test
    fun `Get const in singleton and assert same`() {
        val constValue1 = Singleton.name
        val constValue2 = Singleton.name

        Assert.assertSame(constValue1, constValue2)
    }
}