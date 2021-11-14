package com.baeldung.singleton

import org.junit.Assert
import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.Executors
import kotlin.math.sin
import kotlin.random.Random

/**
 *
 */
class SingletonWithParamTest {
    @Test
    fun `Get two singleton object and assert them same`() {
        val param = "Test Param"
        val singletonA = SingletonWithParam.getInstance(param)
        val singletonB = SingletonWithParam.getInstance(param)

        Assert.assertSame(singletonA, singletonB)
        Assert.assertEquals(singletonA.toString(), singletonB.toString())
    }

    @Test
    fun `Get two singleton object with different param and assert all param same`() {
        val paramA = "Test Param"
        val singletonA = SingletonWithParam.getInstance(paramA)

        val paramB = "Test ParamB"
        val singletonB = SingletonWithParam.getInstance(paramB)

        Assert.assertSame(paramA, singletonA.paramValue)
        Assert.assertSame(singletonA.paramValue, singletonB.paramValue)
        Assert.assertNotSame(paramB, singletonA.paramValue)
        Assert.assertNotEquals(paramB, singletonA.paramValue)
    }

    @Test
    fun `Create multi singleton and test thread safe`() {
        val param: String = "InitParam"
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