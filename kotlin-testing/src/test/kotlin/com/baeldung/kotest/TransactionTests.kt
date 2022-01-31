package com.baeldung.kotest

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.framework.concurrency.FixedInterval
import io.kotest.framework.concurrency.eventually
import io.kotest.matchers.shouldBe

class TransactionTests : ShouldSpec({
    val transactionRepo = TransactionRepo()

    should("Should make transaction complete") {
        eventually({
            duration = 5000
            interval = FixedInterval(1000)
        }) {
            transactionRepo.getStatus(120) shouldBe "COMPLETE"
        }
    }
})