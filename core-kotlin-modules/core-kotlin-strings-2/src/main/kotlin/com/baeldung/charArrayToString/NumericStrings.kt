package com.baeldung.charArrayToString

object NumericStrings {
    fun stringConstructor() {
        val charArray = charArrayOf('b', 'a', 'e', 'l')
        val convertedString = String(charArray)
        println("Converted string is: $convertedString")
    }

    fun joinToString() {
        val charArray: Array<Char> = arrayOf('b', 'a', 'e', 'l')
        val convertedString = charArray.joinToString()
        println("Converted string join is: $convertedString")
    }

    fun stringBuilder() {
        val charArray = charArrayOf('b', 'a', 'e', 'l')
        val convertedString = StringBuilder().append(charArray).toString()
        println("Converted string builder is: $convertedString")
    }
}
