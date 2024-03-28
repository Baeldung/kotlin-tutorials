package com.baeldung.schedulingrepeatingtask

import java.util.Timer
import kotlin.concurrent.schedule

fun main(){
    val timer = Timer()
    timer.schedule(0, 1000) {
        println("Timer ticked!")
    }
}