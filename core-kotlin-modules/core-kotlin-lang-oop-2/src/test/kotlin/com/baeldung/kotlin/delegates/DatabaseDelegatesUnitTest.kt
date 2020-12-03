package com.baeldung.kotlin.delegates

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DatabaseDelegatesUnitTest {
    @Test
    fun testGetKnownFields() {
        val user = User(1)
        assertEquals("George", user.name)
        assertEquals(4, user.age)
    }

    @Test
    fun testSetKnownFields() {
        val user = User(2)
        user.age = 3
        assertEquals(3, user.age)
    }

    @Test
    fun testGetKnownField() {
		assertFailsWith(NoRecordFoundException::class) {
            val user = User(3)
            user.name
        }
    }
}
