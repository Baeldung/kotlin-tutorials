package com.baeldung.threadsafe

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
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
            Thread.sleep(10) // Simulate processing time
            synchronized(to) {
                if (balance >= amount) {
                    withdraw(amount)
                    to.deposit(amount)
                }
            }
        }
    }
}

class ThreadSafeUnitTest {

    private val logger = LoggerFactory.getLogger("")

    @Test
    fun `example of deadlock`() {
        val account1 = Account("Hangga", 1000)
        val account2 = Account("John", 1000)
        val account3 = Account("Alice", 2000)

        // Transfer from account1 to account2
        thread {
            account1.transfer(account2, 100)
        }

        // Transfer from account2 to account1
        thread {
            account2.transfer(account1, 200)
        }

        // Transfer from account3 to account1
        thread {
            account3.transfer(account1, 1000)
        }

        logger.info("${account1.name}'s actual balance is: ${account1.balance}, expected: 2100")
        logger.info("${account2.name}'s actual balance is: ${account2.balance}, expected: 900")
        logger.info("${account3.name}'s actual balance is: ${account3.balance}, expected: 1000")
    }

    @Test
    fun `test using mutex to prevent deadlock free`() = runBlocking {
        val account1 = Account("Hangga", 1000)
        val account2 = Account("John", 1000)
        val account3 = Account("Alice", 2000)

        val mutex = Mutex()

        // Transfer from account1 to account2
        mutex.withLock {
            account1.transfer(account2, 100)
        }

        // Transfer from account2 to account1
        mutex.withLock {
            account2.transfer(account1, 200)
        }

        // Transfer from account3 to account1
        mutex.withLock {
            account3.transfer(account1, 1000)
        }

        assertEquals(2100, account1.balance)
        assertEquals(900, account2.balance)
        assertEquals(1000, account3.balance)
    }


    @Test
    fun `example of race condition`() {
        val mutableList = mutableListOf<Int>()

        thread {
            for (i in 1..100) {
                mutableList.add(i)
            }
        }

        thread {
            for (i in 101..200) {
                mutableList.add(i)
            }
        }

        thread {
            for (i in 201..300) {
                mutableList.add(i)
            }
        }

        logger.info("${mutableList.size}")
    }

    @Test
    fun `test using synchronized to prevent race-condition`() {
        val mutableList = mutableListOf<Int>()

        val lock = Any()

        val threads = listOf(thread {
            for (i in 1..100) {
                synchronized(lock) {
                    mutableList.add(i)
                }
            }
        }, thread {
            for (i in 101..200) {
                synchronized(lock) {
                    mutableList.add(i)
                }
            }
        }, thread {
            for (i in 201..300) {
                synchronized(lock) {
                    mutableList.add(i)
                }
            }
        })
        threads.forEach {
            it.join()
        }

        assertEquals(300, mutableList.size)
    }

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
    fun `test using AtomicInteger to prevent race-condition`() {
        val list = mutableListOf<Int>()
        val atomInt = AtomicInteger(0)

        thread {
            for (i in 1..100) {
                list.add(i)
                atomInt.incrementAndGet() // Increment the atomic counter
            }
        }.join()

        thread {
            for (i in 101..200) {
                list.add(i)
                atomInt.incrementAndGet()
            }
        }.join()

        assertEquals(200, list.size)
    }

    @Test
    fun `test using AtomicInteger and synchronized to prevent race-condition reducing potentially deadlock`() {
        val list = mutableListOf<Int>()
        val atomInt = AtomicInteger(0)

        val threads = listOf(thread {
            for (i in 1..100) {
                synchronized(list) {
                    list.add(i)
                    atomInt.incrementAndGet()
                }
            }
        }, thread {
            for (i in 101..200) {
                synchronized(list) {
                    list.add(i)
                    atomInt.incrementAndGet()
                }
            }
        }, thread {
            for (i in 201..300) {
                synchronized(list) {
                    list.add(i)
                    atomInt.incrementAndGet()
                }
            }
        })

        threads.forEach { it.join() }

        val actualSize = list.size
        val countedSize = atomInt.get()
        logger.info("Actual list size: $actualSize, Counter value: $countedSize")

        if (actualSize != 300 || countedSize != 300) {
            logger.warn("Possible race condition detected! List size or counter is incorrect.")
        }
    }

    @Test
    fun `test using Collections-synchronizedMap to prevent thread-safety issue`() {
        val map = Collections.synchronizedMap(HashMap<Int, String>())

        thread {
            for (i in 1..100) {
                map[i] = "Thread 1 - $i"
                Thread.sleep(100) // simulate delay
            }
        }.join()

        thread {
            for (i in 101..200) {
                map[i] = "Thread 2 - $i"
            }
        }.join()

        thread { map.remove(200) }.join()

        assertEquals(199, map.size)
    }

    @Test
    fun `test using ConcurrentHashMap for alternative to Collections-synchronizedMap`() {
        val map = ConcurrentHashMap<Int, String>() // prevent race-condition

        thread {
            for (i in 1..100) {
                map[i] = "Thread 1 - $i"
            }
        }.join() // wait until thread finishes to prevent ConcurrentModificationException

        thread {
            for (i in 101..200) {
                map[i] = "Thread 2 - $i"
            }
        }.join()

        thread { map.remove(200) }.join()

        assertEquals(199, map.size)
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