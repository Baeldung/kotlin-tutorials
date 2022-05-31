package com.baeldung.kotlin.collection.ops

import java.math.BigDecimal
import java.util.Currency

data class DataClass(
    val fieldA: String,
    val fieldB: String,
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val age: Int,
    val salary: BigDecimal,
    val currency: Currency,
    val child: InnerDataClass
)

data class InnerDataClass(val fieldA: String, val fieldB: String)

fun changeField(input: DataClass, newValue: String): DataClass = input.copy(fieldA = newValue)