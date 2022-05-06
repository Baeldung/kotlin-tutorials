package com.baeldung.universalObjects

private fun main() {
    val storage1 = DataClass.Storage("980Pro M.2 NVMe", 1024)
    val storage2 = DataClass.Storage("980Pro M.2 NVMe", 1024)
    val dataClass = DataClass()
    println(dataClass.areEqual(storage1, storage2))
}
class DataClass{
    fun areEqual(obj1: Storage, obj2: Storage): Boolean {
        return obj1 == obj2
    }
    data class Storage(private val name: String, private val capacity: Int)
}

