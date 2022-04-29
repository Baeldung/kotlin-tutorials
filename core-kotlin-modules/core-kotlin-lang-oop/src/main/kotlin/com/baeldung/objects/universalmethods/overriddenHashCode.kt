package com.baeldung.objects.universalmethods

fun main() {
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

    val storage1 = Storage("980Pro M.2 NVMe", 1024)
    val storage2 = Storage("980Pro M.2 NVMe", 1024)
    println(storage1 == storage2)
}