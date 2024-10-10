package com.baeldung.threadsafe

import kotlinx.collections.immutable.persistentSetOf
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.test.assertFailsWith

class ThreadSafeUnitTest {

	@Test
	fun `tes unsafe mutableList`() {
		assertFailsWith<ConcurrentModificationException> {
			val list = mutableListOf<Int>()

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
	}

	@Test
	fun `tes using persistentSetOf`() {
		var list = persistentSetOf<Int>()

		for (i in 1..10) {
			for (j in 1..100) {
				list = list.add(j)
			}
		}

		for (item in list) {
			if (item == 50) {
				list = list.remove(item)
			}
		}

		assertTrue(50 !in list)
	}

	@Test
	fun `tes thread-safe with CopyOnWriteArrayList`() {
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
	fun `tes thread-safe with ConcurrentLinkedQueue`() {
		val queue = ConcurrentLinkedQueue<Int>()

		for (i in 1..10) {
			for (j in 1..100) {
				queue.add(j)
			}
		}

		for (item in queue) {
			if (item == 50) {
				queue.remove(item)
			}
		}

		assertTrue(50 !in queue)
	}

	@Test
	fun `tes thread-safe with coroutines`() = runBlocking {
		val list = mutableListOf<Int>()

		val job1 = async {
			for (i in 1..10) {
				for (j in 1..100) {
					list.add(j)
				}
			}
		}

		val job2 = async {
			job1.await()  // Tunggu sampai job1 selesai
			val iterator = list.iterator()
			while (iterator.hasNext()) {
				if (iterator.next() == 50) {
					iterator.remove() // Ini thread-safe dalam coroutine context
				}
			}
		}

		job2.await()
		assertTrue(50 !in list)
	}

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