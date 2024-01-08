package com.baeldung.enum

enum class Color(val brightness: Int) {
    RED(10), GREEN(5), BLUE(8)
}

enum class Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
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

fun getColorInfo(color: Color): String {
    return when (color) {
        Color.RED -> "A vibrant color with brightness ${color.brightness}"
        Color.GREEN -> "A soothing color with brightness ${color.brightness}"
        Color.BLUE -> "A calming color with brightness ${color.brightness}"
    }
}
