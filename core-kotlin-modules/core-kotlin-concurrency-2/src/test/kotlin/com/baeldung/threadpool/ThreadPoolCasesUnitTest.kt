package com.baeldung.threadpool

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ThreadPoolCasesUnitTest {

    @Test
    fun `when slow IO offloaded to another thread then the main task proceeds faster`() {
        val worker = SingleThreadTaskManager()
        worker.work()
        worker.close()
    }

    @Test
    fun `when custom thread factory is used then names of the threads can be customized`() {
        val worker = Executors.newFixedThreadPool(4, object : ThreadFactory {
            private val counter = AtomicInteger(0)
            override fun newThread(r: Runnable): Thread =
              Thread(null, r, "panda-thread-${counter.incrementAndGet()}")
        })

        buildList<Callable<String>> {
            repeat(4) { add({ Thread.currentThread().name }) }
        }
          .let(worker::invokeAll)
          .map { it.get() }
          .all { it.startsWith("panda-thread") }
          .also { assertTrue(it) }

    }

    @Test
    fun `when task is scheduled then it performs regularly`() {
        val counter = AtomicInteger(0)
        val worker = Executors.newSingleThreadScheduledExecutor()
        worker.scheduleAtFixedRate({ counter.incrementAndGet() }, 0, 100, TimeUnit.MILLISECONDS)
        Thread.sleep(400)
        assertTrue { counter.get() > 2 }
    }

    class Task(val started: Long) : Callable<Long> {
        override fun call() = (System.currentTimeMillis() - started)
          .also { Thread.sleep(100) }
    }

    @Test
    fun `when queue is longer than the list of threads then the tasks have to wait`() {
        val workerQueue = LinkedBlockingQueue<Runnable>(/*unbounded*/)
        val worker = ThreadPoolExecutor(
          2,
          2,
          0,
          TimeUnit.SECONDS,
          workerQueue,
          Executors.defaultThreadFactory(),
          ThreadPoolExecutor.AbortPolicy()
        )
        buildList {
            repeat(5) { add(Task(System.currentTimeMillis())) }
        }.let(worker::invokeAll)
          .map(Future<Long>::get)
          .also { println(it) }
    }

    @Test
    fun `when queue is overflown than the rejection policy is invoked`() {
        val workerQueue = LinkedBlockingQueue<Runnable>(3)
        val worker = ThreadPoolExecutor(
          2,
          2,
          0,
          TimeUnit.SECONDS,
          workerQueue,
          Executors.defaultThreadFactory(),
          ThreadPoolExecutor.AbortPolicy()
        )
        buildList {
            repeat(3) { add(Task(System.currentTimeMillis())) }
        }
          .let(worker::invokeAll)
          .map { it.get() }
          .also { println(it) }


        buildList {
            repeat(6) { add(Task(System.currentTimeMillis())) }
        }
          .let { tasks ->
              assertThrows<RejectedExecutionException> { worker.invokeAll(tasks) }
          }
    }

    @Test
    fun `when the thread pool is shut down then it doesn't accept more tasks`() {
        val worker = Executors.newFixedThreadPool(2)
        buildList {
            repeat(5) { add(Task(System.currentTimeMillis())) }
        }.map(worker::submit)
        worker.shutdown()
        assertThrows<RejectedExecutionException> { worker.submit { println("any task, really") } }
    }

    @Test
    fun `when we await the thread pool termination then it takes time`() {
        val worker = Executors.newFixedThreadPool(2)
        buildList {
            repeat(5) { add(Task(System.currentTimeMillis())) }
        }.map(worker::submit)
        assertFalse { worker.awaitTermination(10, TimeUnit.MILLISECONDS) }
    }

    @Test
    fun `when the thread pool is shut down NOW then it doesn't wait`() {
        val worker = Executors.newFixedThreadPool(2)
        buildList {
            repeat(50) { add(Task(System.currentTimeMillis())) }
        }.map(worker::submit)
        val unrun: List<Runnable> = worker.shutdownNow()
        assertTrue { unrun.isNotEmpty() }
    }
}