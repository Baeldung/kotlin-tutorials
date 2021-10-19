package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.*

fun main() : Unit = runBlocking {
    val start = System.currentTimeMillis()

    val job = launch {
        try{
            var i = 0
            while (i < 10) {
                yield()
                if (System.currentTimeMillis() > start + (i + 1) * 300)
                    println("Doing non suspendable stuff no.${i++}")
            }
        }
        catch(ce: CancellationException){
            println("Job cancelled")
            throw ce
        }
        finally{
            println("Job ended")
        }
    }

    delay(1000L)
    job.cancelAndJoin()
    println("Job completed")
}

