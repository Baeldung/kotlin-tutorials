package com.baeldung.universalObjects

private fun main() {
    val storage1 = EqualityOnObjects.Storage("980Pro M.2 NVMe", 1024)
    val storage2 = EqualityOnObjects.Storage("980Pro M.2 NVMe", 1024)
    val equalityOnObject = EqualityOnObjects()
    println(equalityOnObject.areEqual(storage1, storage2))
}

class EqualityOnObjects {
    fun areEqual(obj1: Storage, obj2: Storage): Boolean {
        return obj1 == obj2
    }

    class Storage(private val name: String, private val capacity: Int)
}
