package com.baeldung.assignwhile

fun simpleVarAssignmentOutsideLoop() {
    val sentinelValue = 10
    var value: Int = 0
    while (value != sentinelValue) {
        // do something...
        value++
    }
}

fun whileWithBreakStatement() {
    val sentinelValue = 10
    while (true) {
        val value = getValue()
        if (value == sentinelValue) break
        // Process value
    }
}

fun usingDoWhile() {
    val sentinelValue = 10
    do {
        val value = getValue()
        if (value == sentinelValue) break
        // Process value
    } while (true)
}

private fun getValue() = 10