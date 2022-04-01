package com.baeldung.cancelcoroutines

import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class FakeAsyncTask(private val taskResult: Int, private val callback: Callback) {
    private val service = ScheduledThreadPoolExecutor(1) { r ->
        Executors.defaultThreadFactory().newThread(r).apply { isDaemon = true }
    }

    val future = FutureTask {
        println("fetching result")
        Thread.sleep(100)
        when(taskResult) {
            1 -> callback.completed(1)
            2 -> callback.cancelled()
            else -> callback.failed()
        }
    }

    fun start() {
        println("submitted task")
        service.schedule(future, 50, TimeUnit.MILLISECONDS)
    }
}
