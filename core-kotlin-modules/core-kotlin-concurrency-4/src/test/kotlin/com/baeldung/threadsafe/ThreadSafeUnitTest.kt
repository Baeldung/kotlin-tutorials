package com.baeldung.threadsafe

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger
import kotlin.ConcurrentModificationException
import kotlin.collections.HashMap
import kotlin.test.assertFailsWith

class ThreadSafeUnitTest {

	private val logger = LoggerFactory.getLogger("")

	@Test
	fun `test sample ConcurrentModificationException`() {
		val list = mutableListOf(1, 2, 3, 4, 5)

		val thread1 = Thread {
			for (item in list) {
				assertFailsWith<ConcurrentModificationException> {
					logger.info("$item")
				}
			}
		}

		val thread2 = Thread {
			list.removeAt(0)
		}

		thread1.start()
		thread2.start()
	}

	@Test
	fun `test prevent ConcurrentModificationException with mutex`() = runBlocking {
		val list = mutableListOf(1, 2, 3, 4, 5)
		val mutex = Mutex()

		val job1 = launch {
			for (item in list) {
				mutex.withLock {
					logger.info("$item")
				}
			}
		}

		val job2 = launch {
			mutex.withLock {
				list.removeAt(2)
			}
		}

		job1.join()
		job2.join()

		assertTrue(3 !in list)
	}

	@Test
	fun `test sample Race-condition`() {
		val list = mutableListOf<Int>()

		val thread1 = Thread {
			for (i in 1..1000) {
				try {
					list.add(i)
				}catch (e: Exception) {
					logger.error(e.message)
				}
			}
		}

		val thread2 = Thread {
			for (i in 1001..2000) {
				try {
					list.add(i)
				}catch (e: Exception) {
					logger.error(e.message)
				}
			}
		}

		thread1.start()
		thread2.start()
		thread1.join()
		thread2.join()

		val actualSize = list.size
		logger.info("Actual list size: $actualSize")

		if (actualSize != 2000) {
			logger.warn("Possible race condition detected! List size is incorrect: expected 2000 but got $actualSize.")
		}
	}

	@Test
	fun `test using AtomicInteger to preventRace-condition`() {
		val list = mutableListOf<Int>()
		val atomInt = AtomicInteger(0)

		val thread1 = Thread {
			for (i in 1..1000) {
				synchronized(list) {
					list.add(i)
					atomInt.incrementAndGet() // Increment the atomic counter
				}
			}
		}

		val thread2 = Thread {
			for (i in 1001..2000) {
				synchronized(list) {
					list.add(i)
					atomInt.incrementAndGet()
				}
			}
		}

		thread1.start()
		thread2.start()
		thread1.join()
		thread2.join()

		assertEquals(2000, list.size)
	}

	@Test
	fun `test sample deadlock`() {
		val lock1 = Any()
		val lock2 = Any()

		val thread1 = Thread {
			synchronized(lock1) {
				logger.info("Holding lock 1...")
				Thread.sleep(100)

				logger.info("Waiting for lock 2...")
				synchronized(lock2) {
					logger.info("Thread 1: Holding lock 1 and lock 2")
				}
			}
		}

		val thread2 = Thread {
			synchronized(lock2) {
				logger.info("Holding lock 2...")
				Thread.sleep(100)

				logger.info("Waiting for lock 1...")
				synchronized(lock1) {
					logger.info("Holding lock 2 and lock 1")
				}
			}
		}

		thread1.start()
		thread2.start()

		val timeoutMillis = 3000L // 3 seconds
		thread1.join(timeoutMillis)
		thread2.join(timeoutMillis)

		assertTrue(thread1.isAlive && thread2.isAlive, "Deadlock detected: Both threads are still alive.")
	}

	@Test
	fun `test prevent deadlock with ConcurrentLinkedQueue`() {
		val queue = ConcurrentLinkedQueue<Int>()

		val thread1 = Thread {
			for (i in 1..1000) {
				logger.info("thread1: Adding $i to the queue")
				queue.add(i)
				Thread.sleep(100) // simulate delay
			}
		}

		val thread2 = Thread {
			while (true) {
				val item = queue.poll() // Get and remove the first element from the queue
				if (item != null) {
					logger.info("thread2: Processing $item")
				} else {
					Thread.sleep(100) // If the queue is empty, wait a moment
				}
			}
		}

		thread1.start()
		thread2.start()
	}

	@Test
	fun `prevent deadlock with Collections-synchronizedMap`() {
		val map = Collections.synchronizedMap(HashMap<Int, String>())

		val thread1 = Thread {
			for (i in 1..5) {
				logger.info("Thread 1: Adding $i to the map")
				map[i] = "Value $i"
				Thread.sleep(100) // simulate delay
			}
		}

		val thread2 = Thread {
			for (i in 1..5) {
				logger.info("Thread 2: Accessing value for key $i")
				val value = map[i]
				logger.info("Thread 2: Retrieved value: $value")
				Thread.sleep(100)
			}
		}

		thread1.start()
		thread2.start()

		thread1.join()
		thread2.join()
	}

//
//	@Test
//	fun `tes unsafe mutableList`() {
//		assertFailsWith<ConcurrentModificationException> {
//			val list = mutableListOf<Int>()
//
//			for (i in 1..10) {
//				for (j in 1..100) {
//					list.add(j)
//				}
//			}
//
//			for (item in list) {
//				if (item == 50) {
//					list.remove(item)
//				}
//			}
//
//			assertTrue(50 !in list)
//		}
//	}
//
//	@Test
//	fun `tes using persistentSetOf`() {
//		var list = persistentSetOf<Int>()
//
//		for (i in 1..10) {
//			for (j in 1..100) {
//				list = list.add(j)
//			}
//		}
//
//		for (item in list) {
//			if (item == 50) {
//				list = list.remove(item)
//			}
//		}
//
//		assertTrue(50 !in list)
//	}
//
//	@Test
//	fun `tes thread-safe with CopyOnWriteArrayList`() {
//		val list = CopyOnWriteArrayList<Int>()
//
//		for (i in 1..10) {
//			for (j in 1..100) {
//				list.add(j)
//			}
//		}
//
//		for (item in list) {
//			if (item == 50) {
//				list.remove(item)
//			}
//		}
//
//		assertTrue(50 !in list)
//	}
//
//	@Test
//	fun `tes thread-safe with ConcurrentLinkedQueue`() {
//		val queue = ConcurrentLinkedQueue<Int>()
//
//		for (i in 1..10) {
//			for (j in 1..100) {
//				queue.add(j)
//			}
//		}
//
//		for (item in queue) {
//			if (item == 50) {
//				queue.remove(item)
//			}
//		}
//
//		assertTrue(50 !in queue)
//	}
//
//	@Test
//	fun `tes thread-safe with coroutines`() = runBlocking {
//		val list = mutableListOf<Int>()
//
//		val job1 = async {
//			for (i in 1..10) {
//				for (j in 1..100) {
//					list.add(j)
//				}
//			}
//		}
//
//		val job2 = async {
//			job1.await()  // waiting until job1 finish
//			val iterator = list.iterator()
//			while (iterator.hasNext()) {
//				if (iterator.next() == 50) {
//					iterator.remove() // thread-safe in coroutine context
//				}
//			}
//		}
//
//		job2.await()
//		assertTrue(50 !in list)
//	}
//
	@Test
	fun `test using mutex`() = runBlocking {
		val list = mutableListOf<Int>()
		val mutex = Mutex()

		for (i in 1..10) {
			for (j in 1..100) {
				mutex.withLock {
					list.add(j) // modifiying list with safe
				}
			}
		}

		for (k in list.toList()) {
			if (k == 50) {
				mutex.withLock {
					list.remove(k) // remove elements while iterating safely
				}
			}
		}

		assertTrue(50 !in list)
	}
}