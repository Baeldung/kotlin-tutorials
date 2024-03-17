package com.baeldung.schedulingrepeatingtask

import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.schedule

class SchedulingRepeatingTaskUnitTest{

    @Test
    fun `using timer`(){
        val latch = CountDownLatch(1)
        val timer = Timer()
        timer.schedule(0L, 1000L) {
            println("Timer ticked!")
            latch.countDown()
        }
        latch.await()
    }
}