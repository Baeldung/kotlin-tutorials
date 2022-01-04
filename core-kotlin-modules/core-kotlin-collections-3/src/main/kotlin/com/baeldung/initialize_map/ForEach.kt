package com.baeldung.iterate_map

class InitializeMap {
    private fun mapOfFunction() {
        val mapWithoutValues = mapOf<String, String>()
        val emptyMap = emptyMap<String, String>()

        val mapWithValues = mapOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")
    }

    private fun mutableMapOfFunction() {
        val emptyMutableMap = mutableMapOf<String, String>()
        emptyMutableMap["Key"] = "Value"

        val mutableMap = mutableMapOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")
        mutableMap["Key3"] = "Value10" // modify value
        mutableMap["Key4"] = "Value4" // add entry
        mutableMap.remove("Key1") // delete existing value
    }

    private fun hashMapOfFunction() {
        val hashMap = hashMapOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")
        hashMap["Key3"] = "Value10" // modify value
        hashMap["Key4"] = "Value4" // add entry
        hashMap.remove("Key1") // delete existing value
    }

    private fun sortedMapOfFunction() {
        val sortedMap = sortedMapOf("Key3" to "Value3", "Key1" to "Value1", "Key2" to "Value2")
    }

    private fun linkedMapOfFunction() {
        val linkedMap = linkedMapOf("Key3" to "Value3", "Key1" to "Value1", "Key2" to "Value2")
    }
}
