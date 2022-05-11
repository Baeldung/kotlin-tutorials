package com.baeldung.universalObjects

private fun main() {
    val storage1 = Storage("980Pro M.2 NVMed", 1024)
    val storage2 = Storage("980Pro M.2 NVMe", 1024)

    println(storage1 == storage2)
}

class Storage(private val name: String, private val capacity: Int)

