package com.baeldung.array

fun main(args: Array<String>) {
    val fruits = arrayOf("Pear", "Apple", "Papaya", "Banana")
    println(fruits.associate { Pair(it, it.length) })
    println(fruits.associateBy { it.length })
    println(fruits.associateWith { it.length })

    // associateWithTo and associateByTo
    val nameVsLengthMap = mutableMapOf("Pomegranate" to 11, "Pea" to 3)
    val lengthVsNameMap = mutableMapOf(11 to "Pomegranate", 3 to "Pea")

    println(fruits.associateWithTo(nameVsLengthMap, { it.length }))
    println(fruits.associateByTo(lengthVsNameMap, { it.length }))
}