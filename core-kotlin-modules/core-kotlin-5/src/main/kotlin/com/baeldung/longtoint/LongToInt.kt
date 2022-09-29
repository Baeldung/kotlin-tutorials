package com.baeldung.longtoint

class LongToInt {
    private fun Long.toIntOrNull(): Int? {
        return if (this >= Int.MIN_VALUE && this <= Int.MAX_VALUE) {
            toInt()
        } else {
            null
        }
    }
}