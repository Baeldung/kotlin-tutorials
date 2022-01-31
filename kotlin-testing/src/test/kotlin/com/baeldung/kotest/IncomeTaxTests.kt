package com.baeldung.kotest

import com.baeldung.kotest.TaxClass.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class TaxTestData(val income: Long, val taxClass: TaxClass, val expectedTaxAmount: Long)

class IncomeTaxTests : FunSpec({
    withData(
            TaxTestData(1000, ONE, 300),
            TaxTestData(1000, TWO, 350),
            TaxTestData(1000, THREE, 200)
    ) { (income, taxClass, expectedTaxAmount) ->
        calculateTax(income, taxClass) shouldBe expectedTaxAmount
    }
})