package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.*

var printLnNumber = 0

fun main() : Unit = runBlocking {
    val job = launch {
        try{
            repeat(10) { i ->
                delay(300L)
                println("Fetching album no.$i (line ${++printLnNumber})")
            }
        }
        catch(ce: CancellationException){
            println("Fetching albums cancelled (line ${++printLnNumber})")
            throw ce
        }
        finally{
            println("Fetching albums ended, (line ${++printLnNumber})")
        }
    }

    delay(1000L)
    job.cancelAndJoin()
    println("Fetching albums job completed, lines printed: $printLnNumber")
}