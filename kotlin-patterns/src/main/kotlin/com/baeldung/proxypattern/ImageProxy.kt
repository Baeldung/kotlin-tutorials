package com.baeldung.proxypattern

interface Image {
    fun display(): Unit
}
class RealImage(private val filename: String) : Image {
    init {
        loadFromDisk()
    }
    private fun loadFromDisk() {
        println("Loading image: $filename")
    }
    override fun display() {
        println("Displaying image: $filename")
    }
}
class ProxyImage(private val filename: String) : Image {
    private var realImage: RealImage? = null
    override fun display() {
        if (realImage == null) {
            realImage = RealImage(filename)
        }
        realImage?.display()
    }
}


