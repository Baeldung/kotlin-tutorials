package com.baeldung.universalObjects

private fun main() {
    val storage1 = DataClassStorage("980Pro M.2 NVMe", 1024)
    val storage2 = DataClassStorage("980Pro M.2 NVMe", 1024)

    println(storage1 == storage2)
}

data class DataClassStorage(private val name: String, private val capacity: Int)


