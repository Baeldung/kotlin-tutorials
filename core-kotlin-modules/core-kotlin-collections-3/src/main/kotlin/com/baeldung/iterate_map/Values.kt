package com.baeldung.iterate_map

class Values {
    private val map = mapOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")
    private fun valuesForLoop() {
        for (value in map.values) {
            print(value)
        }
    }

    private fun valuesForEachExtension() {
        map.values.forEach { value ->
            print(value)
        }
    }
}
