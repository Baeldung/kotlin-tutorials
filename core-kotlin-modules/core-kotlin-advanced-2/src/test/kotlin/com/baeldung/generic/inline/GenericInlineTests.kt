package com.baeldung.generic.inline

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GenericInlineTests {

    @Test
    fun `Find User with Generic Inline Classes`() {
        val user1 = User(Id("1"), "Alice")
        val user2 = User(Id("2"), "Bob")
        val wallet = Wallet(Id(1), "Bitcoin")

        val foundUser = findById(listOf(user1, user2, wallet), Id("2"))

        assertEquals(user2, foundUser)
    }

    @Test
    fun `Calculate distance`() {
        val distance1 = Distance(5)
        val distance2 = Distance(10)

        val totalDistance = distance1 + distance2

        assertEquals(15, totalDistance.value)
    }

}
