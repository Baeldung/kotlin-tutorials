package com.baeldung.patternmatching

import org.junit.jupiter.api.Test

class PatternMatchingUnitTest {

    private val mailOrder = MailOrder("Mail Order", "My Adress")

    @Test
    fun `Patter matching Mail Order example`() {
        val response = generateResponse(mailOrder);
        println(response)
    }

    fun generateResponse(order: Order): String {
        val s = when (order) {
            is MailOrder -> {
                val (name, address) = order
                "Thank you $name, you will receive confirmation through post at: $address"
            }

            is WebOrder -> {
                val (name, email) = order
                "Thank you $name, you will receive confirmation through email at $email"
            }
        }
        return s
    }

}