package com.baeldung.singleton

import org.junit.Assert
import org.junit.jupiter.api.Test

class SingletonTest {
    @Test
    fun `Get singleton object id and assert equals`() {
        val objectId1 = Singleton.toString()
        val objectId2 = Singleton.toString()
        println("Test1:")
        println(objectId1)
        println(objectId2)
        Assert.assertEquals(objectId1, objectId2)
    }

    @Test
    fun `Get const in singleton and assert same`() {
        val constValue1 = Singleton.name
        val constValue2 = Singleton.name
        println("Test2:")
        println(constValue1)
        println(constValue2)
        Assert.assertSame(constValue1, constValue2)
    }

    @Test
    fun `Call singleton function outside and get the right result`() {
        val result = Singleton.add(3, 5);
        Assert.assertEquals(result, 3 + 5)
    }
}