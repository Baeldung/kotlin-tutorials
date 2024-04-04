package com.baeldung.iteratePropertiesOfDataClass

fun main(a: Array<String>) {
    val cls = Person("John", 38)
    for(field in cls) {
        println(field)
    }
}
