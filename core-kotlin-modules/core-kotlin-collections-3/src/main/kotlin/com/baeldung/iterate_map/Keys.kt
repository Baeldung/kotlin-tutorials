package com.baeldung.iterate_map

class Keys {
    private val map = mapOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")
    private fun keysForLoop() {
        for (key in map.keys) {
            val value = map[key]
            print("$key : $value")
        }
    }

    private fun keysForEachExtension() {
        map.keys.forEach { key ->
            val value = map[key]
            print("$key : $value")
        }
    }
}
