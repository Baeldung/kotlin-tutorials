package com.baeldung.kotlin.multiplatform

fun add(num1: Double, num2: Double): Double {
    val sum = num1 + num2
    writeLogMessage("The sum of $num1 & $num2 is $sum", LogLevel.DEBUG)
    return sum
}

fun subtract(num1: Double, num2: Double): Double {
    val diff = num1 - num2
    writeLogMessage("The difference of $num1 & $num2 is $diff", LogLevel.DEBUG)
    return diff
}

fun multiply(num1: Double, num2: Double): Double {
    val product = num1 * num2
    writeLogMessage("The product of $num1 & $num2 is $product", LogLevel.DEBUG)
    return product
}

fun divide(num1: Double, num2: Double): Double {
    val division = num1 / num2
    writeLogMessage("The division of $num1 & $num2 is $division", LogLevel.DEBUG)
    return division
}

