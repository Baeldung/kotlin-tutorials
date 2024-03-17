package com.baeldung.schedulingrepeatingtask

import java.util.Timer
import kotlin.concurrent.schedule

fun main(){
    var count = 0
    val timer = Timer()
    timer.schedule(0L, 1000L) {
        println("Timer ticked!")
        count++
        if (count > 10) timer.cancel()
    }

}