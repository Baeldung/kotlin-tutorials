package com.baeldung.coroutinescancellation

import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch(Dispatchers.Main) {
        withTimeout(500L) {
            println("This is printed before the delay")
            delay(1300L)
            println("This will never get printed")
        }
    }
}
