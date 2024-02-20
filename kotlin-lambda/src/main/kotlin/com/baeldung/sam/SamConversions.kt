package com.baeldung.sam

import java.util.concurrent.Callable
import java.util.concurrent.Executors

@Suppress("ObjectLiteralToLambda", "MoveLambdaOutsideParentheses")
fun main() {
    Thread(object : Runnable {
        override fun run() {
            // the logic
        }
    })

    Thread({
        // the logic
    })

    Thread {
        // the logic
    }

    var answer = 42
    Thread { println(answer) }

    val executor = Executors.newFixedThreadPool(2)
    executor.submit(Callable { return@Callable 42 })

    Predicate<Int> { i -> i == 42 }
}

fun doSomething(): Runnable = Runnable {
    // doing something
}

fun interface Predicate<T> {
    fun accept(element: T): Boolean
}