package com.baeldung.threadsafe

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep
import java.lang.management.ManagementFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class Account(val name: String, var balance: Int) {

    private fun deposit(amount: Int) {
        balance += amount
    }

    private fun withdraw(amount: Int) {
        balance -= amount
    }

    fun transfer(to: Account, amount: Int) {
        println("${this.name} tries to transfer $amount to ${to.name}.")
        synchronized(this) {
            sleep(10) // Simulate processing time
            if (balance >= amount) {
                withdraw(amount)
                synchronized(to) {
                    to.deposit(amount)
                }
            }
        }
    }
}

class PageViewCounterAtomic {
    private val visitCount = AtomicInteger(0)

    fun visitPage() {
        visitCount.incrementAndGet() // Atomically increment the counter
    }

    fun getTotalVisits(): Int {
        return visitCount.get() // Get the current value
    }
}

class PageViewCounter {
    private var visitCount = 0

    fun visitPage() {
        visitCount++
    }

    fun getTotalVisits(): Int {
        return visitCount
    }
}

class ThreadSafeUnitTest {

    private val logger = LoggerFactory.getLogger("")

    @Test
    fun `example of ConcurrentModificationException`() {
        val list = mutableListOf(1, 2, 3, 4, 5)
        assertFailsWith<ConcurrentModificationException> {
            for (item in list) {
                if (item == 3) {
                    list.remove(item)
                }
            }
        }
    }

    @Test
    fun `test using CopyOnWriteArrayList prevent ConcurrentModificationException`() {
        val list = CopyOnWriteArrayList(mutableListOf(1, 2, 3, 4, 5))

        for (item in list) {
            if (item == 3) {
                list.remove(item)
            }
        }
    }

    @Test
    fun `example of deadlock`() {
        val account1 = Account("Hangga", 1000)
        val account2 = Account("John", 1000)
        val account3 = Account("Alice", 2000)

        // Transfer from account1 to account2
        thread {
            account1.transfer(account2, 100)
        }.join(10) // as a simulation of the time required

        // Transfer from account2 to account1
        thread {
            account2.transfer(account1, 200)
        }.join(10)

        // Transfer from account3 to account1
        thread {
            account3.transfer(account1, 1000)
        }.join(500)

        logger.info("${account1.name}'s actual balance is: ${account1.balance}, expected: 2100")
        logger.info("${account2.name}'s actual balance is: ${account2.balance}, expected: 900")
        logger.info("${account3.name}'s actual balance is: ${account3.balance}, expected: 1000")
    }

    @Test
    fun `test thread-safe using mutex to prevent deadlock`() = runBlocking {
        val account1 = Account("Hangga", 1000)
        val account2 = Account("John", 1000)
        val account3 = Account("Alice", 2000)

        val mutex = Mutex()

        // Transfer from account1 to account2
        mutex.withLock {
            account1.transfer(account2, 100)
            delay(10) // as a simulation of the time required
        }

        // Transfer from account2 to account1
        mutex.withLock {
            account2.transfer(account1, 200)
            delay(20)
        }

        // Transfer from account3 to account1
        mutex.withLock {
            account3.transfer(account1, 1000)
            delay(500)
        }

        assertEquals(2100, account1.balance)
        assertEquals(900, account2.balance)
        assertEquals(1000, account3.balance)
    }

    @RepeatedTest(10)
    fun `example of race-condition`() {
        val mutableList = mutableListOf<Int>()

        val thread1 = thread {
            for (i in 1..100) {
                mutableList.add(i)
                sleep(10) // Add small delay
            }
        }

        val thread2 = thread {
            for (i in 101..200) {
                mutableList.add(i)
                sleep(10)
            }
        }

        val thread3 = thread {
            for (i in 201..300) {
                mutableList.add(i)
                sleep(10)
            }
        }

        thread1.join()
        thread2.join()
        thread3.join()

        logger.info("expected size: 300, actual size:${mutableList.size}")
        assertTrue(mutableList.size < 300)
    }

    @Test
    fun `test thread-safe using synchronized`() {
        val mutableList = mutableListOf<Int>()

        val thread1 = thread {
            for (i in 1..100) {
                synchronized(mutableList) {
                    mutableList.add(i)
                    sleep(10) // Add small delay
                }

            }
        }

        val thread2 = thread {
            for (i in 101..200) {
                synchronized(mutableList) {
                    mutableList.add(i)
                    sleep(10)
                }
            }
        }

        val thread3 = thread {
            for (i in 201..300) {
                synchronized(mutableList) {
                    mutableList.add(i)
                    sleep(10)
                }
            }
        }

        thread1.join()
        thread2.join()
        thread3.join()

        assertEquals(300, mutableList.size)
    }

    @RepeatedTest(100)
    fun `example thread-unsafe view page counter`() {
        val counter = PageViewCounter()

        val threads = List(10) {
            thread {
                repeat(100) {
                    counter.visitPage() // Each thread increments the counter 100 times
                }
            }
        }

        threads.forEach { it.join() }

        val actualCount = counter.getTotalVisits()
        logger.info("Page view count: $actualCount " + if (actualCount == 1000) "✅" else "❌")
    }

    @RepeatedTest(100)
    fun `test thread-safe view counter using atomic integer`() {
        val counter = PageViewCounterAtomic()

        val threads = List(10) {
            thread {
                repeat(100) {
                    counter.visitPage() // Each thread increments the counter 100 times
                }
            }
        }

        threads.forEach { it.join() }
        assertTrue(counter.getTotalVisits() == 1000)
    }

    @RepeatedTest(10)
    fun `example thread-unsafe using HashMap`() {
        val map = HashMap<Int, Int>()
        val threads = List(10) { index ->
            thread {
                for (i in 0 until 1000) {
                    map[i] = index
                }
            }
        }

        threads.forEach { it.join() }

        val actualSize = map.size
        logger.info("HashMap size: $actualSize " + if (actualSize == 1000) "✅" else "❌")
    }

    @RepeatedTest(10)
    fun `test thread-safe using ConcurrentHashMap`() {
        val map = ConcurrentHashMap<Int, Int>()

        val threads = List(10) { index ->
            thread {
                for (i in 0 until 1000) {
                    map[i] = index
                }
            }
        }

        threads.forEach { it.join() }

        val actualSize = map.size
        assertEquals(1000, actualSize)
    }

    @RepeatedTest(10)
    fun `test thread-safe using Collections-synchronizedMap`() {
        val map = Collections.synchronizedMap(HashMap<Int, Int>())

        val threads = List(10) { index ->
            thread {
                for (i in 0 until 1000) {
                    map[i] = index
                }
            }
        }

        threads.forEach { it.join() }

        val actualSize = map.size
        assertEquals(1000, actualSize)
    }

    @AfterEach
    fun detectDeadlock() {
        val threadMXBean = ManagementFactory.getThreadMXBean()
        threadMXBean.findDeadlockedThreads()?.forEach { id ->
            val threadInfo = threadMXBean.getThreadInfo(id, Int.MAX_VALUE)
            logger.warn("Deadlock detected: [id:$id, name:${threadInfo.threadName}, owner:${threadInfo.lockOwnerName}]")
            logger.warn(threadInfo.stackTrace.joinToString("\n"))
        }
    }
}