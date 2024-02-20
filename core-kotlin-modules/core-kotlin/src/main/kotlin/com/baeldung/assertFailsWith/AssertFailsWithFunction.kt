package com.baeldung.assertFailsWith

class AssertFailsWithFunction {
    fun assertFailsWithMessage(){
        val array = intArrayOf(1, 2, 3)
        array[5]
    }

    fun assertFailsWithExceptionClass(){
        "Kotlin Tutorials in Baeldung".toInt()
    }

    fun assertFailsWithMessageAndExceptionClass(){
        @Suppress("DIVISION_BY_ZERO")
        50 * 12 / 0
    }
}