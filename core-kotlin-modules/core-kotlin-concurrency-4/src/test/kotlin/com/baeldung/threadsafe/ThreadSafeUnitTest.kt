package com.baeldung.threadsafe

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.management.ManagementFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ThreadSafeUnitTest {

	private val logger = LoggerFactory.getLogger("")

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
	fun `example of ConcurrentModificationException`() {
		val list = mutableListOf(1, 2, 3, 4, 5)

		thread {
			for (item in list) {
				assertFailsWith<ConcurrentModificationException> {
					Thread.sleep(200)
					logger.info("$item")
				}
			}
		}

		thread {
			list.removeAt(2)
		}
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
	fun `test using synchronized prevent race condition and ConcurrentModificationException`() {
		val list = mutableListOf<Int>()
		val lock = Any()

		for (i in 1..10) {
			for (j in 1..100) {
				synchronized(lock) {
					list.add(j)
				}
			}
		}

		thread {
			synchronized(list) {
				for (item in list) {
					logger.info("$item")
				}
			}
		}.join()

		synchronized(lock) {
			list.remove(100)
		}

		assertEquals(999, list.size)
	}

	@Test
	fun `test using AtomicInteger to prevent race-condition still potentially deadlock`() {
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
	fun `test using ConcurrentLinkedQueue to prevent deadlock`() {
		val queue = ConcurrentLinkedQueue<Int>()

		thread {
			for (i in 1..100) {
				logger.info("thread1: Adding $i to the queue")
				queue.add(i)
				Thread.sleep(100) // simulate delay
			}
		}

		thread {
			while (true) {
				val item = queue.poll() // Get and remove the first element from the queue
				if (item != null) {
					logger.info("thread2: Processing $item")
				} else {
					Thread.sleep(100) // If the queue is empty, wait a moment
				}
			}
		}

		Thread.sleep(2000) // Berikan waktu untuk deadlock terjadi
	}

	@Test
	fun `test using Collections-synchronizedMap to prevent deadlock`() {
		val map = Collections.synchronizedMap(HashMap<Int, String>())

		thread {
			for (i in 1..5) {
				logger.info("Thread 1: Adding $i to the map")
				map[i] = "Value $i"
				Thread.sleep(100) // simulate delay
			}
		}.join()

		thread {
			for (i in 1..5) {
				logger.info("Thread 2: Accessing value for key $i")
				val value = map[i]
				logger.info("Thread 2: Retrieved value: $value")
				Thread.sleep(100)
			}
		}.join()
	}

	@Test
	fun `test using ConcurrentHashMap to prevent race condition and ConcurrentModificationException`() {
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

		//detectDeadlock()
		assertEquals(200, map.size)
	}

	@Test
	fun `test using CopyOnWriteArrayList to prevent deadlock`() {
		val list = CopyOnWriteArrayList<Int>()

		for (i in 1..10) {
			for (j in 1..100) {
				list.add(j)
			}
		}

		for (item in list) {
			if (item == 50) {
				list.remove(item)
			}
		}
		assertTrue(50 !in list)
	}

	@Test
	fun `test using coroutines-Mutex with safe dispatcher`() = runBlocking {
		val list = mutableListOf<Int>()
		val mutex = Mutex()

		val jobs = List(size = 10) { _ ->
			launch(Dispatchers.Default) {
				for (j in 1..100) {
					mutex.withLock { // ensure only one coroutine accesses the list at a time.
						list.add(j)
					}
				}
			}
		}

		jobs.forEach { it.join() }

		withContext(Dispatchers.Default) {
			mutex.withLock {
				list.removeAll(listOf(100))
			}
		}

		assertTrue(100 !in list)
		assertEquals(990, list.size)
	}


	@Test
	fun `example of deadlock`() {
		val lock1 = Any()
		val lock2 = Any()

		thread {
			synchronized(lock1) {
				logger.info("Holding lock1...")
				Thread.sleep(100)

				logger.info("Waiting for lock2...")
				synchronized(lock2) {
					logger.info("Holding lock1 and lock2")
				}
			}
		}

		thread {
			synchronized(lock2) {
				logger.info("Holding lock2...")
				Thread.sleep(100)

				logger.info("Waiting for lock1...")
				synchronized(lock1) {
					logger.info("Holding lock2 and lock1")
				}
			}
		}

		Thread.sleep(2000)
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