package com.baeldung.comparison

fun main(number: Int): String {
    return when (number) {
        0 -> "Zero"
        1, 2 -> "One or Two"
        in 3..5 -> "Between Three and Five"
        else -> "Other"
    }
}


fun main() {
    val dayOfWeek = 3
    when (dayOfWeek) {
        1 -> println("Monday")
        2 -> println("Tuesday")
        3 -> println("Wednesday")
        4 -> println("Thursday")
        5 -> println("Friday")
        else -> println("Weekend")
    }
}
