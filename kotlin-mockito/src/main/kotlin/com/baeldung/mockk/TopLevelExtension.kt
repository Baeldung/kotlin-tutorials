package com.baeldung.mockk

fun topLevelFunction(): String {
    return "Hello, World!"
}

fun String.greet(): String {
    return "Hello, $this"
}