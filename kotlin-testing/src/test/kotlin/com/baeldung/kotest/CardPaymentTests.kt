package com.baeldung.kotest

import io.kotest.core.spec.style.BehaviorSpec

class CardPaymentTests : BehaviorSpec({
    given("I have sufficient balance") {
        `when`("I make a card payment") {
            then("The card payment should be successful") {
                // test code
            }
        }
    }
})