package com.baeldung.longtoint

class LongToInt {

    private fun usingToInt() {
        val longValue = 100L
        val intValue = longValue.toInt()
    }

    private fun Long.toIntOrNull(): Int? {
        return if (this >= Int.MIN_VALUE && this <= Int.MAX_VALUE) {
            toInt()
        } else {
            null
        }
    }
}