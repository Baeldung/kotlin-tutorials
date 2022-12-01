package com.baeldung.universalobjects

class ObjectComparison {
    class Storage(private val name: String, private val capacity: Int)
    class StorageOverriddenEquals(val name: String, val capacity: Int) {
        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (this === other) return true
            if (other !is StorageOverriddenEquals) return false
            if (name != other.name || capacity != other.capacity) return false
            return true
        }
    }
    class StorageOverriddenEqualsAndHashCode(private val name: String, private val capacity: Int) {
        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (this === other) return true
            if (other !is StorageOverriddenEqualsAndHashCode) return false
            other as StorageOverriddenEqualsAndHashCode
            if (name != other.name || capacity != other.capacity) return false
            return true
        }

        override fun hashCode(): Int = name.hashCode() * 31 + capacity
    }
    data class StorageDataClass(private val name: String, private val capacity: Int)

    fun compareStrings(){
        val a = "Baeldung"
        val b = "Baeldung"
        println("compareStrings() returns ${a == b}")
    }

    fun compareSimpleObjects(){
        val storage1 = Storage("980Pro M.2 NVMe", 1024)
        val storage2 = Storage("980Pro M.2 NVMe", 1024)

        println("compareSimpleObjects() returns ${storage1 == storage2}")
    }

    fun compareOverriddenEquals(){
        val storage1 = StorageOverriddenEquals("980Pro M.2 NVMe", 1024)
        val storage2 = StorageOverriddenEquals("980Pro M.2 NVMe", 1024)

        println("compareOverriddenEquals() returns ${storage1 == storage2}")
    }

    fun compareOverriddenEqualsAndHashCode(){
        val storage1 = StorageOverriddenEqualsAndHashCode("980Pro M.2 NVMe", 1024)
        val storage2 = StorageOverriddenEqualsAndHashCode("980Pro M.2 NVMe", 1024)

        println("compareOverriddenEqualsAndHashCode() returns ${storage1 == storage2}")
    }

    fun compareDataClasses(){
        val storage1 = StorageDataClass("980Pro M.2 NVMe", 1024)
        val storage2 = StorageDataClass("980Pro M.2 NVMe", 1024)

        println("compareDataClasses() returns ${storage1 == storage2}")
    }
}

fun main(){
    val objectComparison = ObjectComparison()
    objectComparison.compareStrings()
    objectComparison.compareSimpleObjects()
    objectComparison.compareOverriddenEquals()
    objectComparison.compareOverriddenEqualsAndHashCode()
    objectComparison.compareDataClasses()
}