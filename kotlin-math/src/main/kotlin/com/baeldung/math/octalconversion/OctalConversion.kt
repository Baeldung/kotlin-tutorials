package com.baeldung.math.octalconversion

import java.math.BigInteger

fun octalToDecimalUsingParseInt(octal: String): Int {
    return Integer.parseInt(octal, 8)
}

fun octalToDecimalUsingIteration(octal: String): Int {
    var decimal = 0
    var power = 0
    for (i in octal.length - 1 downTo 0) {
        val digit = octal[i].toString().toInt()
        decimal += digit * Math.pow(8.0, power.toDouble()).toInt()
        power++
    }
    return decimal
}

fun String.octalToDecimal(): Int {
    return Integer.parseInt(this, 8)
}

fun octalToDecimalUsingToInt(octal: String): Int {
    return octal.toInt(8)
}

fun octalToDecimalUsingBigInteger(octal: String): BigInteger {
    return BigInteger(octal, 8)
}

fun decimalToOctalUsingIntegerToString(decimal: Int): String {
    return Integer.toString(decimal, 8)
}

fun decimalToOctalUsingStringInterpolation(decimal: Int): String {
    return "%o".format(decimal)
}

fun Int.decimalToOctal(): String {
    return Integer.toString(this, 8)
}

fun decimalToOctalUsingIteration(decimal: Int): String {
    var octal = ""
    var num = decimal
    while (num > 0) {
        octal = (num % 8).toString() + octal
        num /= 8
    }
    return octal
}