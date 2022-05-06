package com.baeldung.call_function.after_delay

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.lang3.time.StopWatch
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class CallFunctionAfterDelay {

    fun scheduleWithTimer() {
        val stopWatch = StopWatch.createStarted()
        val timer = Timer()
            timer.schedule(object : TimerTask() {
            override fun run() {
                stopWatch.stop()
                println("Function in scheduleWithTimer executed with delay " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.time))
                timer.cancel()
            }
        }, 1000)
    }

    fun scheduleWithExecutor() {
        val stopWatch = StopWatch.createStarted()
        val executor = Executors.newSingleThreadScheduledExecutor()
            executor.schedule({
                stopWatch.stop()
                println("Function in scheduleWithExecutor executed with delay " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.time))
                executor.shutdown()
            }, 2, TimeUnit.SECONDS)
    }

    fun scheduleWithCoroutine() = runBlocking {
        val stopWatch = StopWatch.createStarted()
        launch {
            delay(3000L)
            stopWatch.stop()
            println("Function in scheduleWithCoroutine executed with delay " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.time))
        }
    }
}

fun main() {
    val testObject = CallFunctionAfterDelay()
    testObject.scheduleWithTimer()
    testObject.scheduleWithExecutor()
    testObject.scheduleWithCoroutine()
}