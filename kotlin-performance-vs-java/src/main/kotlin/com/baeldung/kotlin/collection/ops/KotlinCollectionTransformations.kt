package com.baeldung.kotlin.collection.ops


fun mapCollection() =
    (1..100_000).map { java.lang.String.valueOf(it) }

fun mapSmallCollection() =
    (1..10).map { java.lang.String.valueOf(it) }

fun transformStringList(strings: List<String>) =
    strings.map { it + System.currentTimeMillis() }