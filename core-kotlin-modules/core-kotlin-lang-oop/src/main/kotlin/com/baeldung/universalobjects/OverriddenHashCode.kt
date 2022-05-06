package com.baeldung.universalObjects

fun main() {
    val storage1 = OverriddenHashCode.Storage("980Pro M.2 NVMe", 1024)
    val storage2 = OverriddenHashCode.Storage("980Pro M.2 NVMe", 1024)
    val overriddenHashCode = OverriddenHashCode()
    println(overriddenHashCode.areEqual(storage1, storage2))
}
class OverriddenHashCode{
    fun areEqual(obj1: Storage, obj2: Storage): Boolean {
        return obj1 == obj2
    }

    class Storage(private val name: String, private val capacity: Int){
        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (this === other) return true
            if (other !is Storage) return false
            other as Storage
            if (name != other.name || capacity != other.capacity) return false
            return true
        }
        override fun hashCode(): Int = name.hashCode() * 31 + capacity
    }
}
