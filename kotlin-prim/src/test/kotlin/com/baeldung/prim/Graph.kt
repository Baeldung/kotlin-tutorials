package com.baeldung.prim

data class Graph(val edges: Collection<Edge>) {
    fun getNodes() : Collection<String> {
        return edges.flatMap { setOf(it.first, it.second) }.distinct()
    }

    fun getEdgesForNode(node: String) : Collection<Edge> {
        return edges.filter { it.first == node || it.second == node }
    }
}
