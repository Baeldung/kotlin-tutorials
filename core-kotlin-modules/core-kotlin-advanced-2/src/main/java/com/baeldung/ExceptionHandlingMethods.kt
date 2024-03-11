package com.baeldung

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        try {
            val result = 10 / 0
            println("Result: $result")
        } catch (e: ArithmeticException) {
            println("Caught an ArithmeticException: $e")
        }
    }
    delay(1000)
}


fun main1() = runBlocking {
    try {
        coroutineScope {
            launch {
                delay(200)
                throw CustomException("An exception occurred!")
            }
            launch {
                delay(100)
                println("This coroutine completes successfully.")
            }
        }
    } catch (e: CustomException) {
        println("Caught exception: ${e.message}")
    }
}

class CustomException(message: String) : Exception(message)

fun main2() = runBlocking {
    supervisorScope {
        val job = launch {
            launch {
                delay(200)
                throw CustomException("An exception occurred!")
            }
            launch {
                delay(100)
                println("This coroutine completes successfully.")
            }
        }
        job.join()
    }
}

fun main3() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Caught global exception: ${exception.message}")
    }
    val job = GlobalScope.launch(exceptionHandler) {
        delay(100)
        throw CustomException("An exception occurred!")
    }
    job.join()
}