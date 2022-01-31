package com.baeldung.kotest

enum class TaxClass(val percent: Int) {
    ONE(30), TWO(35), THREE(20)
}

fun calculateTax(income: Long, taxClass: TaxClass) = income * taxClass.percent / 100