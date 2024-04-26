package com.baeldung.passTypeToGenericMethod

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PassTypeToGenericMethod {
    @Test
    fun `pass type to generic method using reified parameters`() {
        val intValue: Int = passTypeUsingReifiedParameter()
        val stringValue: String = passTypeUsingReifiedParameter()

        assertEquals(42, intValue)
        assertEquals("Generic Method!", stringValue)
    }

    @Test
    fun `pass type to generic method using class parameters`() {
        val intValue = passTypeUsingClassParameter(Int::class.java)
        val stringValue = passTypeUsingClassParameter(String::class.java)

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
inline fun <reified T> passTypeUsingReifiedParameter(): T {
    return when (T::class) {
        Int::class -> 42 as T
        String::class -> "Generic Method!" as T
        else -> throw IllegalArgumentException("Unsupported type")
    }
}
fun <T> passTypeUsingClassParameter(clazz: Class<T>): T {
    return when (clazz) {
        Int::class.java -> 42 as T
        String::class.java -> "Generic Method!" as T
        else -> throw IllegalArgumentException("Unsupported type")
    }
}
fun <T> passTypeUsingTypeCasting(type: T): T {
    return when (type) {
        is Int -> 42 as T
        is String -> "Generic Method!" as T
        else -> throw IllegalArgumentException("Unsupported type")
    }
}