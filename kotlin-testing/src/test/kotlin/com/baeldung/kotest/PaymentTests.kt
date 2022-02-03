package com.baeldung.kotest

import io.kotest.core.spec.style.DescribeSpec

class PaymentTests : DescribeSpec({
    describe("CardPayments") {
        it("Should make a card payment") {
            // test code
        }
    }
    describe("BankTransfers") {
        it("Should make an external bank transfer") {
            // test code
        }
    }
})