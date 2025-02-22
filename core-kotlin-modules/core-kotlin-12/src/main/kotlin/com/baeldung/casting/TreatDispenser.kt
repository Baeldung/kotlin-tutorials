package com.baeldung.casting

import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.collections.firstOrNull

class TreatDispenser<T>(private val treats: MutableList<T> = mutableListOf()) {

    // Dispense the first treat
    fun dispenseTreat(): T? {
        return if (treats.isNotEmpty()) treats.removeFirst() else null
    }

    // Peek at the next treat without removing it
    fun peekNextTreat(): T? {
        return treats.firstOrNull()
    }

    // Add a treat to the dispenser
    fun addTreat(treat: T) {
        treats.add(treat)
    }
}

// Function using * (Star Projection)
fun peekAtNextTreat(dispenser: TreatDispenser<*>) {
    val nextTreat = dispenser.peekNextTreat()
    println("The next treat is: $nextTreat")
}

// Function using Any (restricted to non-nullable types)
fun peekAtNextTreatAny(dispenser: TreatDispenser<Any>) {
    val nextTreat = dispenser.peekNextTreat()
    println("The next treat is: $nextTreat")
}
