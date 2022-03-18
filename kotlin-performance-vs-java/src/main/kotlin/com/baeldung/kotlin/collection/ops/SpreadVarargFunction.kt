package com.baeldung.kotlin.collection.ops

fun concatenate(vararg pieces: String): String = pieces.joinToString()

fun callVarargFunction(pieces: Array<out String>) = concatenate(*pieces)
