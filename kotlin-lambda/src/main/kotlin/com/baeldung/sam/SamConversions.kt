package com.baeldung.sam

import java.util.concurrent.Callable
import java.util.concurrent.Executors

@Suppress("ObjectLiteralToLambda", "MoveLambdaOutsideParentheses")
fun main() {
    val objectExpressionThread = Thread(object : Runnable {
        override fun run() {
            // the logic
        }
    })

    val lambdaThread = Thread({
        // the logic
    })

    val samThread = Thread {
        // the logic
    }

    var answer = 42
    val threadWithClosure = Thread { println(answer) }

    val executor = Executors.newFixedThreadPool(2)
    val submit = executor.submit(Callable { return@Callable 42 })

    val isAnswer = Predicate<Int> { i -> i == 42 }
}

fun doSomething(): Runnable = Runnable {
    // doing something
}

fun interface Predicate<T> {
    fun accept(element: T): Boolean
}