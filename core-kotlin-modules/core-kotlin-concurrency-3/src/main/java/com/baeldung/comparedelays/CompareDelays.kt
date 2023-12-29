package com.baeldung.comparedelays

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


import kotlinx.coroutines.*

val lock = Object()
fun main() {
    runBlocking(Dispatchers.Default) {
        launch(Dispatchers.IO) {
            testWaitThread2()
        }
        testWaitThread1()
    }
}
fun testWaitThread1() = synchronized(lock) {
    lock.wait()
    println("Print second")
}
fun testWaitThread2() = synchronized(lock) {
    println("Print first")
    lock.notify()
}



fun sleepMethod() {
    println("Thread 1 is sleeping...")
    Thread.sleep(2000) // Sleep for 2 seconds
    println("Thread 1 woke up!")
}


fun delayMethod() = runBlocking {
    println("Coroutine 1 is delaying...")
    delay(2000) // Delay for 2 seconds
    println("Coroutine 1 resumed!")
}
