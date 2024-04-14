package com.baeldung.dijkstra

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DijkstraUnitTest {

    @Test
    fun `Should calculate shortest path when using Dijkstra algorithm`() {
        val graph = mapOf(
            1 to listOf(Pair(2, 10), Pair(3, 15)),
            2 to listOf(Pair(4, 12)),
            3 to listOf(Pair(4, 15)),
            4 to listOf(Pair(5, 12),Pair(6, 15)),
            5 to emptyList(),
            6 to emptyList()
        )

        val shortestPaths = dijkstra(graph, 1)
        assertEquals(37, shortestPaths.getValue(6))
    }
}