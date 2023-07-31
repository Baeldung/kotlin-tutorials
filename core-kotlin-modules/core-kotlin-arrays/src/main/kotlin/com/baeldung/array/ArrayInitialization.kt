package com.baeldung.array

fun IntRange.toIntArray(): IntArray {
    val result = IntArray(this.count())
    var index = 0
    for (element in this)
        result[index++] = element
    return result
}