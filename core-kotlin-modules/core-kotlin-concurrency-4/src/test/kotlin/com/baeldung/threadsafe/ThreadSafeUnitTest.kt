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

class Account(private val name: String, private var balance: Int) {

	private fun deposit(amount: Int) {
		balance += amount
	}

	private fun withdraw(amount: Int) {
		balance -= amount
	}

	fun transfer(to: Account, amount: Int) {
		println("Try transfer to ${to.name}, $amount")
		synchronized(this) {
			Thread.sleep(50) // Simulate processing time
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
	fun `example of deadlock in finance transaction simulation`() {
		val accountA = Account("AccountA", 1000)
		val accountB = Account("AccountB", 1000)

		// Thread 1: Transfer from accountA to accountB
		val t1 = thread {
			accountA.transfer(accountB, 100)
		}

		// Thread 2: Transfer from accountB to accountA
		val t2 = thread {
			accountB.transfer(accountA, 200)
		}

		// Set a timeout for deadlock assumption
		val timeout = 2000L // 2 seconds
		val startTime = System.currentTimeMillis()

		while (t1.isAlive || t2.isAlive) {
			if (System.currentTimeMillis() - startTime > timeout) {
				t1.interrupt() // Attempt to stop t1
				t2.interrupt() // Attempt to stop t2
				break
			}
			Thread.sleep(100) // Check every 100 milliseconds
		}
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

	@Test
	fun `test using coroutines-Mutex with safe dispatcher`() = runBlocking {
		val list = mutableListOf<Int>()
		val mutex = Mutex()

		val jobs = List(size = 10) {
			launch(Dispatchers.Default) {
				for (j in 1..100) {
					mutex.withLock { // ensure only one coroutine accesses the list at a time.
						list.add(j)
						logger.info("Thread-safe operation add : $j")
					}
				}
			}
		}

		jobs.forEach { it.join() }

		withContext(Dispatchers.Default) {
			mutex.withLock {
				list.removeAll(listOf(100))
				logger.info("Thread-safe operation removeAll : ${listOf(100)}")
			}
		}

		assertTrue(100 !in list)
		assertEquals(990, list.size)
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