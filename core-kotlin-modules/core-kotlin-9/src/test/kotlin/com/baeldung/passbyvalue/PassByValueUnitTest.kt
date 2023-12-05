package com.baeldung.passbyvalue

import org.junit.Assert.assertEquals
import org.junit.Test

class PassByValueUnitTest {

    data class SomeObj(var x: Int = 0)

    fun modifyObject(o: SomeObj) {
        o.x = 3
    }

    fun modifyValue(value: Int): Int {
        return value + 10
    }

    @Test
    fun `test using pass by reference`() {
        val obj = SomeObj()
        obj.x = 10
        assertEquals(obj.x, 10) // before modify
        modifyObject(obj)
        assertEquals(obj.x, 3) // after modify
    }

    @Test
    fun `test using pass-by-value`(){
        var num = 5
        val modifiedNum = modifyValue(num)
        assertEquals(5, num)
        assertEquals(15, modifiedNum)
    }
}