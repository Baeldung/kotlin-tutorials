package com.baeldung.quasar

import co.paralleluniverse.fibers.Fiber
import co.paralleluniverse.strands.SuspendableRunnable


/**
 * Entrypoint into the application
 */
fun main() {
    class Runnable : SuspendableRunnable {
        override fun run() {
            println("Hello")
        }
    }
    val result = Fiber<Void>(Runnable()).start()
    result.join()
    println("World")
}
