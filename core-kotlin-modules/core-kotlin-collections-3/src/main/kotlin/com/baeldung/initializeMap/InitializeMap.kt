package com.baeldung.initializeMap

class InitializeMap {
    private fun mapOfFunction() {
        val mapWithoutValues = mapOf<String, String>()
        println("mapWithoutValues is: $mapWithoutValues")
        val emptyMap = emptyMap<String, String>()
        println("emptyMap is: $emptyMap")

        val mapWithValues = mapOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")
        println("mapWithValues is: $mapWithValues")
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
        println("Sorted map is: $sortedMap")
    }

    private fun linkedMapOfFunction() {
        val linkedMap = linkedMapOf("Key3" to "Value3", "Key1" to "Value1", "Key2" to "Value2")
        println("linkedMap is: $linkedMap")
    }
}
