package com.baeldung.schedulingrepeatingtask

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main(){
    var count = 0
    val scheduler = Executors.newSingleThreadScheduledExecutor()

    scheduler.scheduleAtFixedRate(Runnable {
        println("Complex task completed!")
        count++
        if (count > 10) scheduler.shutdown()
    }, 0L, 1L, TimeUnit.SECONDS)
}