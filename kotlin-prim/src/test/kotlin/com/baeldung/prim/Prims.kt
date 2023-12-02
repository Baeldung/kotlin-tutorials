package com.baeldung.prim

fun prims(graph: Graph) : Graph {
    val visitedNodes = mutableSetOf<String>()
    val edges = mutableSetOf<Edge>()

    visitedNodes.add(graph.getNodes().random())

    while (!visitedNodes.containsAll(graph.getNodes())) {
        val nextEdge = visitedNodes.flatMap { graph.getEdgesForNode(it) }
            .filter { !visitedNodes.contains(it.first) || !visitedNodes.contains(it.second) }
            .minBy { it.weight }

        visitedNodes.addAll(setOf(nextEdge.first, nextEdge.second))
        edges.add(nextEdge)
    }

    return Graph(edges)
}