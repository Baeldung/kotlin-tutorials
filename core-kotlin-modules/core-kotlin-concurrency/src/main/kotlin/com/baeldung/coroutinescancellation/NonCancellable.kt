package com.baeldung.coroutinescancellation

import kotlinx.coroutines.*

fun main() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        try {
            while (true) {
                println("the job is still running")
                delay(500L)
                yield()
            }
        } finally {
            withContext(NonCancellable) {
                delay(3000L)
                println("this executes no matter what!")
                // write code here that must absolutely run before exit
            }
        }
    }

    GlobalScope.launch(Dispatchers.Default) {
        delay(1300)
        job.cancel()
    }
}
