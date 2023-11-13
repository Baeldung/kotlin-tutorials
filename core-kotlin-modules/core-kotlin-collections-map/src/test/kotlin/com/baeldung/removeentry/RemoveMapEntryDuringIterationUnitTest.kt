package com.baeldung.removeentry

import org.junit.jupiter.api.Test
import java.util.ConcurrentModificationException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RemoveMapEntryDuringIterationUnitTest {

    @Test
    fun `test removing entry from map while iterating using for loop should remove successfully`() {
        val map = mutableMapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val iterator = map.entries.iterator()

        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.key % 2 == 0) {
                iterator.remove()
            }
        }

        assertEquals(4, map.size)
    }


    @Test
    fun `test removing entry from map while iterating with for loop should fail with ConcurrentModificationException`() {
        val map = mutableMapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        assertFailsWith<ConcurrentModificationException> {
            for ((key, value) in map) {
                if (key % 2 == 0) {
                    map.remove(key)
                }
            }
        }
    }

    @Test
    fun `test removing entry from map using predicate in removeIf method should remove successfully`() {
        val map = mutableMapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        map.entries.removeIf { it.key % 2 == 0 }
        assertEquals(4, map.size)
    }

    @Test
    fun `test removing entry from map using mark and remove strategy should remove successfully`() {
        val map = mutableMapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)

        val candidates = HashSet<Int>()
        // mark phase
        for ((key, value) in map) {
            if (key % 2 == 0) {
                candidates.add(key)
            }
        }
        // delete phase
        for (candidate in candidates) {
            map.remove(candidate)
        }
        assertEquals(4, map.size)
    }
}