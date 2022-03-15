package com.baeldung.char_array_to_string

object NumericStrings {
    fun stringConstructor() {
        val charArray = charArrayOf('b', 'a', 'e', 'l')
        val convertedString = String(charArray)
    }

    fun joinToString() {
        val charArray: Array<Char> = arrayOf('b', 'a', 'e', 'l')
        val convertedString = charArray.joinToString()
    }

    fun stringBuilder() {
        val charArray = charArrayOf('b', 'a', 'e', 'l')
        val convertedString = StringBuilder().append(charArray).toString()
    }
}
