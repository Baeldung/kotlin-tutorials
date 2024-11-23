package com.baeldung.kandy

class DataUtils {
    companion object {
        fun calculateMovingAverage(windowSize: Int, data: List<Double>): List<Double?> {
            val closePrices = data.toList()

            return List(closePrices.size) { index ->
                if (index < windowSize - 1) null
                else {
                    val window = closePrices.subList(index - windowSize + 1, index + 1)
                    window.sum() / window.size
                }
            }
        }
    }
}