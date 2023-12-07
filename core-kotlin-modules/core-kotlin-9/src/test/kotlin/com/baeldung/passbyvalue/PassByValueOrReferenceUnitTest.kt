package com.baeldung.passbyvalue

import org.junit.Assert.assertEquals
import org.junit.Test

class PassByValueOrReferenceUnitTest {

    data class SomeObj(var x: Int = 0)

    private fun modifyObject(o: SomeObj) {
        o.x = 3
    }

    private fun modifyValue(value: Int): Int {
        return value + 10
    }

    @Test
    fun `Test using pass by reference`() {
        val obj = SomeObj()
        obj.x = 10
        assertEquals(10, obj.x) // before modify

        modifyObject(obj)
        assertEquals(3, obj.x) // after modify
    }

    @Test
    fun `Test using pass-by-value`(){
        val num = 5
        val modifiedNum = modifyValue(num)
        assertEquals(5, num)
        assertEquals(15, modifiedNum)
    }
}