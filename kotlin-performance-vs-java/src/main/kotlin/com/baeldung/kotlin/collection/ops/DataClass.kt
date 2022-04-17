package com.baeldung.kotlin.collection.ops

data class DataClass(val fieldA: String)

fun changeField(input: DataClass, newValue: String): DataClass = input.copy(fieldA = newValue)