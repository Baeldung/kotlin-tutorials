package com.baeldung.string.extension_functions

import java.nio.charset.Charset

class StringExtensionFunctionClass {

    fun replaceMethod1(str: String, oldChar: Char, newChar: Char, ignoreCase: Boolean = false) =
            str.replace(oldChar, newChar, ignoreCase)

    fun replaceMethod2(str: String, oldValue: String, newValue: String, ignoreCase: Boolean = false) =
            str.replace(oldValue, newValue, ignoreCase)

    fun uppercaseMethod(str: String) = str.uppercase()

    fun lowercaseMethod(str: String) = str.lowercase()

    fun toCharArrayMethod(str: String) = str.toCharArray()

    fun substringMethod1(str: String, startIndex: Int) = str.substring(startIndex)

    fun substringMethod2(str: String, startIndex: Int, endIndex: Int) = str.substring(startIndex, endIndex)

    fun startsWithMethod1(str: String, prefix: String, ignoreCase: Boolean = false) =
            str.startsWith(prefix, ignoreCase)

    fun startsWithMethod2(str: String, prefix: String, startIndex: Int, ignoreCase: Boolean = false) =
            str.startsWith(prefix, startIndex, ignoreCase)

    fun endsWithMethod(str: String, suffix: String, ignoreCase: Boolean = false) =
            str.endsWith(suffix, ignoreCase)

    fun compareToMethod(str: String, other: String) = str.compareTo(other)

    fun toByteArrayMethod(str: String, charset: Charset = Charsets.UTF_8) = str.toByteArray(charset)

    fun capitalizeMethod(str: String) = str.capitalize()
}