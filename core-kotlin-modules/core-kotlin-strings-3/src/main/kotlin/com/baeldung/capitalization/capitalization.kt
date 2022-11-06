package com.baeldung.capitalization

val NON_CAPITALIZED_WORDS = setOf(
    "as", "at", "but", "by", "for", "in", "of", "off", "on", "per", "to", "up", "via", // short prepositions
    "a", "an", "the", // articles
    "for", "and", "nor", "but", "or", "yet", "so" // coordinating conjunctions
)

fun capitalizeSimpleWay(input: String): String = input
    .split(' ')
    .joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

fun capitalizeWithSequence(input: String): String = sequence {
    var startIndex = 0
    while (startIndex < input.length) {
        val endIndex = input.indexOf(' ', startIndex).takeIf { it > 0 } ?: input.length
        yield(input.substring(startIndex, endIndex))
        startIndex = endIndex + 1
    }
}.map { it.replaceFirstChar(Char::uppercaseChar) }
    .joinToString(" ")

fun capitalizeWithDoubleSpaces(input: String): String = input
    .split("\\W+".toRegex())
    .joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

fun capitalizeWithDoubleSpacesAndSequence(input: String): String = sequence {
    var startIndex = 0
    while (startIndex < input.length) {
        val endIndex = input.findFirstSince(startIndex) { it == ' ' }
        yield(input.substring(startIndex, endIndex))
        startIndex = input.findFirstSince(endIndex) { it != ' ' }
    }
}.map { it.replaceFirstChar(Char::uppercaseChar) }
    .joinToString(" ")

fun capitalizeWithPressRules(input: String): String {
    val components = input.split("\\W+".toRegex())
    return buildString {
        components.forEachIndexed { index, word ->
            when (index) {
                in 1..components.size - 2 -> word.capitalizeMiddleWord() // Some short auxiliary words aren't capitalized
                else -> word.replaceFirstChar(Char::uppercaseChar) // The first and the last words are always capitalized
            }.let { append(it).append(' ') }
        }
        deleteCharAt(length - 1) // Drop the last whitespace
    }
}

private fun String.capitalizeMiddleWord(): String =
    if (length > 3 || this !in NON_CAPITALIZED_WORDS) replaceFirstChar(Char::uppercaseChar) else this

fun String.findFirstSince(position: Int, test: (Char) -> Boolean): Int {
    for (i in position until length) {
        if (test(this[i])) return i
    }
    return length
}