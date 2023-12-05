package com.baeldung.passbyvalue

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PassByValueTest {

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
        Assertions.assertEquals(obj.x, 10)
        modifyObject(obj)
        Assertions.assertEquals(obj.x, 3)
    }

    @Test
    fun `test using pass-by-value`(){
        var num = 5
        val modifiedNum = modifyValue(num)
        Assertions.assertEquals(5, num)
        Assertions.assertEquals(15, modifiedNum)
    }
}