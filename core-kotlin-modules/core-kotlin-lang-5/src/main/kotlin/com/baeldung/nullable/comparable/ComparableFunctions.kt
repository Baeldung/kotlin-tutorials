package com.baeldung.nullable.comparable

fun compareTwoInts(a: Int?, b: Int?): Int? {
    return a?.compareTo(b ?: return null)
}

fun Int?.isGreaterThan(other: Int?): Boolean? {
    return this?.let { it > (other ?: return null) }
}

fun <T : Comparable<T>> T?.isGreaterThan(other: T?): Boolean? {
    return this?.let { it > (other ?: return null) }
}