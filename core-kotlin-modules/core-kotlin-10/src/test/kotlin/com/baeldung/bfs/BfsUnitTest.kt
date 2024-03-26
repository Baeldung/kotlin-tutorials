package com.baeldung.bfs

import org.assertj.core.api.Assertions.assertThat
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

        val traversalOrder = bfs(graph, 1)


        // Level 1
        val levelOne = listOf(traversalOrder.first())
        assertThat(levelOne).containsExactly(1)

        // Level 2
        val levelTwo = traversalOrder.drop(1).take(3)
        assertThat(levelTwo).containsExactlyInAnyOrder(2, 3, 4)

        // Level 3
        val levelThree = traversalOrder.drop(4)
        assertThat(levelThree).containsExactlyInAnyOrder(5, 6, 7, 8)
    }
}