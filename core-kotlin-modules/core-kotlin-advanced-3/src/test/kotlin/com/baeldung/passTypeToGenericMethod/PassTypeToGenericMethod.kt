package com.baeldung.passTypeToGenericMethod

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PassTypeToGenericMethod {
    @Test
    fun `pass type to generic method using reified parameters`() {

        assertEquals("String", passTypeUsingReifiedParameter<String>())
        assertEquals("Int", passTypeUsingReifiedParameter<Int>())
    }

    @Test
    fun `pass type to generic method using class parameters`() {

        assertEquals("String", passTypeUsingClassParameter(String::class.java))
        assertEquals("int", passTypeUsingClassParameter(Int::class.java))
    }

    @Test
    fun `pass type to generic method using higher order functions`() {
        val intValue = passTypeUsingHigherOrderFunction{42}
        val stringValue = passTypeUsingHigherOrderFunction{"Generic Method!"}

        assertEquals(42, intValue)
        assertEquals("Generic Method!", stringValue)
    }

    @Test
    fun `pass type to generic method using type casting`() {
        val intValue = passTypeUsingTypeCasting(0)
        val stringValue = passTypeUsingTypeCasting("")

        assertEquals(42, intValue)
        assertEquals("Generic Method!", stringValue)
    }
}
inline fun <reified T> passTypeUsingReifiedParameter(): String? {
    return T::class.simpleName
}
fun <T> passTypeUsingClassParameter(clazz: Class<T>): String {
    return clazz.simpleName
}
fun <T> passTypeUsingTypeCasting(type: T): T {
    return when (type) {
        is Int -> 42 as T
        is String -> "Generic Method!" as T
        else -> throw IllegalArgumentException("Unsupported type")
    }
}
fun <T> passTypeUsingHigherOrderFunction(action: () -> T): T {
    return action()
}