package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.*

fun main() : Unit = runBlocking {
    val job = launch {
        try{
            repeat(10) { i ->
                delay(300L)
                println("Fetching album no.$i")
                repeat(20) { j ->
                    loadSong(i*100 + j)
                }
            }
        }
        catch(ce: CancellationException){
            println("Fetching albums cancelled")
            throw ce
        }
        finally{
            println("Fetching albums ended")
        }
    }

    delay(1000L)
    job.cancelAndJoin()
    println("Fetching albums job completed")
}

suspend fun loadSong(songId: Int) = coroutineScope {
    launch {
        delay(200L)
        println("Fetching song no.$songId")
    }
}
