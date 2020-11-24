package com.baeldung.kotlin.delegates

import org.junit.jupiter.api.Test
import kotlin.properties.PropertyDelegateProvider
import kotlin.reflect.KProperty
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DelegateProvider<in T, D>(private val field: String, private val id: Int)
    : PropertyDelegateProvider<T, DatabaseDelegate<T, D>> {
    override operator fun provideDelegate(thisRef: T, prop: KProperty<*>): DatabaseDelegate<T, D> {
        println("Providing delegate for field $field and ID $id")
        prop.returnType.isMarkedNullable
        return DatabaseDelegate(field, id)
    }
}

class DelegateProvidingUser(val id: Int) {
    var name: String by DelegateProvider("name", id)
    var age: Int by DelegateProvider("age", id)
}

class DelegateProviderUnitTest {
    @Test
    fun testGetKnownFields() {
        val user = DelegateProvidingUser(1)
        assertEquals("George", user.name)
        assertEquals(4, user.age)
    }

    @Test
    fun testSetKnownFields() {
        val user = DelegateProvidingUser(2)
        user.age = 3
        assertEquals(3, user.age)
    }

    @Test
    fun testGetKnownField() {
		assertFailsWith(NoRecordFoundException::class) {
            val user = DelegateProvidingUser(3)
            user.name
        }
    }
}
