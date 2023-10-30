package com.baeldung.iterateMap

class ForEach {
    private val map = mapOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")
    private fun forEach() {
        map.forEach { entry ->
            print("${entry.key} : ${entry.value}")
        }
    }

    private fun forEachDestructured() {
        map.forEach { (key, value) ->
            print("$key : $value")
        }
    }
}
