package com.baeldung.whenenum

enum class Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
enum class Color {
    RED, GREEN, BLUE
}
fun getColorDescription(color: Color): String {
    return when (color) {
        Color.RED -> "The color is red."
        Color.GREEN -> "The color is green."
        Color.BLUE -> "The color is blue."
    }
}

fun getDailyRoutine(day: Day): String {
    return when (day) {
        Day.MONDAY -> "Start of the workweek"
        Day.WEDNESDAY -> "Midweek - Keep pushing!"
        Day.FRIDAY -> "Almost there, the weekend is coming!"
        Day.SUNDAY -> "Relax and recharge"
        else -> "It's a regular day"
    }
}

fun main() {
    val shape: Shape = Shape.Triangle
    when (shape) {
        Shape.Square -> println("I'm a square")
        Shape.Triangle -> println("I'm a triangle")
    }
}
sealed class Shape {
    object Triangle : Shape()
    object Square : Shape()
}