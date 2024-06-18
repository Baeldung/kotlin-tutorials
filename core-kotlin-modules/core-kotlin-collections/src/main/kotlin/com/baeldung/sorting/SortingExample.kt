package com.baeldung.sorting

import org.slf4j.LoggerFactory

val log = LoggerFactory.getLogger("SortingExample")

fun sortMethodUsage() {
    val sortedValues = mutableListOf(1, 2, 7, 6, 5, 6)
    sortedValues.sort()
    log.info("$sortedValues")
}

fun sortByMethodUsage() {
    val sortedValues = mutableListOf(1 to "a", 2 to "b", 7 to "c", 6 to "d", 5 to "c", 6 to "e")
    sortedValues.sortBy { it.second }
    log.info("$sortedValues")
}

fun sortWithMethodUsage() {
    val sortedValues = mutableListOf(1 to "a", 2 to "b", 7 to "c", 6 to "d", 5 to "c", 6 to "e")
    sortedValues.sortWith(compareBy({ it.second }, { it.first }))
    log.info("$sortedValues")
}

fun <T : kotlin.Comparable<T>> getSimpleComparator(): Comparator<T> {
    val ascComparator = naturalOrder<T>()
    return ascComparator
}

fun getComplexComparator() {
    val complexComparator = compareBy<Pair<Int, String>>({ it.first }, { it.second })
    log.info("Complex comparator result: $complexComparator")
}

fun nullHandlingUsage() {
    val sortedValues = mutableListOf(1 to "a", 2 to null, 7 to "c", 6 to "d", 5 to "c", 6 to "e")
    sortedValues.sortWith(nullsLast(compareBy { it.second }))
    log.info("$sortedValues")
}

fun extendedComparatorUsage() {
    val students = mutableListOf(21 to "Helen", 21 to "Tom", 20 to "Jim")

    val ageComparator = compareBy<Pair<Int, String?>> { it.first }
    val ageAndNameComparator = ageComparator.thenByDescending { it.second }
    students.sortWith(ageAndNameComparator)
    log.info("$students")
}