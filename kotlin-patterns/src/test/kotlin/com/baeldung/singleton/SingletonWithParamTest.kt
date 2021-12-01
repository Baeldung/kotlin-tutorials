package com.baeldung.singleton

import org.junit.Assert
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

/**
 *
 */
class SingletonWithParamTest {
    @Test
    fun `Create multi singleton and test thread safe`() {
        val param = "Test Param"
        val singletonSet = mutableSetOf<SingletonWithParam>()

        val executor = Executors.newFixedThreadPool(5)
        for (i in 0..1000) {
            val worker = Runnable {
                singletonSet.add(SingletonWithParam.getInstance(param + i))
            }
            executor.execute(worker)
        }
        executor.shutdown()
        while (!executor.isTerminated) {
        }

        // Create multi singleton by multi thread, and find all is same
        val singletonHashCode = singletonSet.iterator().next()

        singletonSet.forEach {
            Assert.assertEquals(it, singletonHashCode)
        }
    }
}