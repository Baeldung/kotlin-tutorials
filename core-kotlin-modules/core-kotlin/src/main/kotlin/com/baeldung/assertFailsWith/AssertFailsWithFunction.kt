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
        50 * 12 / 0
    }
}