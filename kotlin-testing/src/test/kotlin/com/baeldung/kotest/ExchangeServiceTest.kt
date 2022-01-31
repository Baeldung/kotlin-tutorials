package com.baeldung.kotest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ExchangeServiceTest : FunSpec({
    val exchangeRateProvider = mockk<ExchangeRateProvider>()
    val service = ExchangeService(exchangeRateProvider)

    test("Exchanges money using rate from exchange rate service") {
        every { exchangeRateProvider.rate("USDEUR") } returns 0.9
        service.exchange(Money(1200, "USD"), "EUR") shouldBe 1080
    }
})