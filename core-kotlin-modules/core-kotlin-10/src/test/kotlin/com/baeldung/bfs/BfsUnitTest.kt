package com.baeldung.bfs

import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Test

class BfsUnitTest {
    @Test
    fun `test BFS traversal order`() {
        val graph = mapOf(
            1 to listOf(2, 3, 4),
            2 to listOf(5, 6),
            3 to listOf(),
            4 to listOf(7, 8),
            5 to listOf(),
            6 to listOf(),
            7 to listOf(),
            8 to listOf()
        )
        val expectedOrder = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        val traversalOrder = bfs(graph, 1)
        assertIterableEquals(expectedOrder, traversalOrder, "The BFS traversal order did not match the expected order.")
    }
}