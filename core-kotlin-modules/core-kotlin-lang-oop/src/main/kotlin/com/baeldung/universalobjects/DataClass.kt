package com.baeldung.universalObjects

class DataClass{
    private fun main() {
        val storage1 = Storage("980Pro M.2 NVMe", 1024)
        val storage2 = Storage("980Pro M.2 NVMe", 1024)
        println(areEqual(storage1, storage2))
    }

    private fun areEqual(obj1: Storage, obj2: Storage): Boolean {
        return obj1 == obj2
    }

    data class Storage(private val name: String, private val capacity: Int)
}

