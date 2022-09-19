package com.baeldung.sum

import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.test.assertEquals

class SumUnitTest {
    @Test
    fun `when the list is empty then the sum is 0`() {
        assertEquals(0, listOf<Int>().sum())
    }

    @Test
    fun `when the list is non-trivial then the sum is properly calculated`() {
        listOf(16, 96, 100, 15, 30, 70, 11, 31, 52, 70, 42, 50, 56, 93, 57)
            .sum()
            .also { assertEquals(789, it) }
    }

    @Test
    fun `when the type overflows then the sum is still calculated`() {
        listOf(Int.MAX_VALUE - 2, 1, 2, 4)
            .sum()
            .also { assertEquals(-2147483644, it) }
    }


    @Test
    fun `when the list is too long then it can be summed in parallel`() {
        val executor = Executors.newFixedThreadPool(4)
        val chunkSize = 3
        listOf(16, 96, 100, 15, 30, 70, 11, 31, 52, 70, 42, 50, 56, 93, 57)
            .asSequence()
            .chunked(chunkSize)
            .map { executor.submit(Callable { it.sum() }) }
            .map { it.get() }
            .sum()
            .also { assertEquals(789, it) }
        executor.shutdownNow()
    }
}
