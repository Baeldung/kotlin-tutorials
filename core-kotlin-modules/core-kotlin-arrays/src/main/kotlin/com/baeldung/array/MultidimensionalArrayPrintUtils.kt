package com.baeldung.array

fun printMultidimArray(array: Array<IntArray>) {
    for (i in array.indices) {
        for (j in array[i].indices) {
            println(array[i][j])
        }
    }
}

fun printMultidimArray(array: Array<Array<Int>>) {
    for (i in array.indices) {
        for (j in array[i].indices) {
            println(array[i][j])
        }
    }
}

fun printMultidimArrayNullable(array: Array<Array<Int>?>) {
    for (i in array.indices) {
        if (array[i] != null) {
            for (j in array[i]!!.indices) {
                println(array[i]!![j])
            }
        }
    }
}