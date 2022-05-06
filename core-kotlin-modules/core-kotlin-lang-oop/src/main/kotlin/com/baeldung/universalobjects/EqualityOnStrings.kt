package com.baeldung.universalObjects

private fun main() {
    val a = "Baeldung"
    val b = "Baeldung"
    val equalityOnStrings = EqualityOnStrings()
    println(equalityOnStrings.areEqual(a, b))
}
class EqualityOnStrings{
    fun areEqual(str1: String, str2: String): Boolean {
        return str1 == str2
    }
}

