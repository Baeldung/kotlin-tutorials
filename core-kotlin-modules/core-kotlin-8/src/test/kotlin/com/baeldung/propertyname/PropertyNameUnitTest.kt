package com.baeldung.propertyname

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PropertyNameUnitTest {
    
    @Test
    fun `can get property name of a class's property statically`() {
        class MyClass(val age: Int, var name: String, val next: MyClass?)

        Assertions.assertEquals("next", MyClass::next.name)
    }

    @Test
    fun `can get property name of an instance's property`() {
        class MyClass(val age: Int, var name: String, val next: MyClass?)
        val instance = MyClass(5, "test", null)

        Assertions.assertEquals("next", instance::next.name)
    }

    @Test
    fun `can get the name of a function`() {
        fun isValid(num: Int, name: String): Boolean = num > 0 && name.isNotBlank()

        Assertions.assertEquals("isValid", ::isValid.name)
    }
}
