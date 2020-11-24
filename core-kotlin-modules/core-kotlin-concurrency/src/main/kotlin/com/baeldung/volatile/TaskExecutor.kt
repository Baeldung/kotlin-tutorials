package com.baeldung.volatile

object TaskExecutor : Runnable {

    @Volatile // comment this out and the program may not terminate at all
    private var shouldContinue = true

    override fun run() {
        while (shouldContinue) {}
        println("Done")
    }

    fun stop() {
        shouldContinue = false
    }
}

fun main() {
    val thread = Thread(TaskExecutor)
    thread.start()

    TaskExecutor.stop()

    thread.join()
}