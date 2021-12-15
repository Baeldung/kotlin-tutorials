package com.baeldung.iterate_map

class ForLoop {
    private val map = mapOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")
    private fun forLoop() {
        for (entry in map) {
            print("${entry.key} : ${entry.value}")
        }
    }

    private fun forLoopExplicit() {
        for (entry in map.entries.iterator()) {
            print("${entry.key} : ${entry.value}")
        }
    }
}
