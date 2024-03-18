package com.baeldung.schedulingrepeatingtask

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main(){
    val scheduler = Executors.newSingleThreadScheduledExecutor()
    scheduler.scheduleAtFixedRate({
        println("Complex task completed!")
    }, 0, 1, TimeUnit.SECONDS)
}