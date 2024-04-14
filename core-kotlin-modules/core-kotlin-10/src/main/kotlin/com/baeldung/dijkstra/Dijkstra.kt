package com.baeldung.dijkstra

import java.util.PriorityQueue

fun dijkstra(graph: Map<Int, List<Pair<Int, Int>>>, start: Int): Map<Int, Int> {
    val distances = mutableMapOf<Int, Int>().withDefault { Int.MAX_VALUE }
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second }).apply {
        add(start to 0)
    }
    distances[start] = 0

    while (priorityQueue.isNotEmpty()) {
        val (node, currentDist) = priorityQueue.poll()
        graph[node]?.forEach { (adjacent, weight) ->
            val totalDist = currentDist + weight
            if (totalDist < distances.getValue(adjacent)) {
                distances[adjacent] = totalDist
                priorityQueue.add(adjacent to totalDist)
            }
        }
    }
    return distances
}

fun dijkstraWithLoops(graph: Map<Int, List<Pair<Int, Int>>>, start: Int): Map<Int, Int> {
    val distances = mutableMapOf<Int, Int>().withDefault { Int.MAX_VALUE }
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    val visited = mutableSetOf<Pair<Int, Int>>()

    priorityQueue.add(start to 0)
    distances[start] = 0

    while (priorityQueue.isNotEmpty()) {
        val (node, currentDist) = priorityQueue.poll()
        if (visited.add(node to currentDist)) {
            graph[node]?.forEach { (adjacent, weight) ->
                val totalDist = currentDist + weight
                if (totalDist < distances.getValue(adjacent)) {
                    distances[adjacent] = totalDist
                    priorityQueue.add(adjacent to totalDist)
                }
            }
        }
    }
    return distances
}
