package com.baeldung.anonymous

import java.io.Serializable
import java.nio.channels.Channel

fun main() {
    object : Channel {
        override fun isOpen() = false

        override fun close() {
        }
    }

    val maxEntries = 10
    object : LinkedHashMap<String, Int>(10, 0.75f) {

        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, Int>?): Boolean {
            return size > maxEntries
        }
    }

    object : LinkedHashMap<String, Int>() {
        // omitted
    }

    object : Channel, Serializable {
        override fun isOpen(): Boolean {
            TODO("Not yet implemented")
        }

        override fun close() {
            TODO("Not yet implemented")
        }
    }

    val obj = object {
        val question = "answer"
        val answer = 42
    }
    println("The ${obj.question} is ${obj.answer}")
}