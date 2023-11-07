package com.baeldung.countvowelconsonant


val VOWELS = "aAeEiIoOuU"

fun countUsingLoopAndIfCondition(sentence: String): Pair<Int, Int> {
    var vowelCount = 0
    var consonantCount = 0

    for (char in sentence) {
        if (char.isLetter()) {
            if (char in VOWELS) {
                vowelCount++
            } else {
                consonantCount++
            }
        }
    }
    return vowelCount to consonantCount
}

fun countUsingLoopAndWhenExpression(sentence: String): Pair<Int, Int> {
    var vowelCount = 0
    var consonantCount = 0

    for (char in sentence) {
        when {
            char.isLetter() && char in VOWELS -> vowelCount++
            char.isLetter() && char !in VOWELS -> consonantCount++
        }
    }
    return vowelCount to consonantCount
}


fun countUsingFilter(sentence: String): Pair<Int, Int> {
    val filteredChars = sentence.filter { it.isLetter() }
    val vowelCount = filteredChars.count { it in VOWELS }
    val consonantCount = filteredChars.count() - vowelCount

    return vowelCount to consonantCount
}


fun countUsingFold(sentence: String): Pair<Int, Int> {
    val (vowelCount, consonantCount) = sentence.fold(0 to 0) { acc, char ->
        if (char.isLetter()) {
            if (char in VOWELS) {
                acc.first + 1 to acc.second
            } else {
                acc.first to acc.second + 1
            }
        } else {
            acc
        }
    }
    return vowelCount to consonantCount
}


fun countUsingHigherOrderFunction(sentence: String, condition: (Char) -> Boolean): Int {
    return sentence.count(condition)
}

fun countUsingRegex(sentence: String): Pair<Int, Int> {
    val vowelCount = Regex("[$VOWELS]").findAll(sentence).count()
    val consonantCount = Regex("[a-zA-Z]").findAll(sentence).count() - vowelCount
    return vowelCount to consonantCount
}

fun countUsingMap(sentence: String): Pair<Int, Int> {
    val letterCounts = sentence.groupingBy { it }
        .eachCount()
    val vowelCount = letterCounts.filterKeys { it in VOWELS }.values.sum()
    val consonantCount = letterCounts.filterKeys { it.isLetter() && it !in VOWELS }.values.sum()

    return vowelCount to consonantCount
}