package com.baeldung.execjar

import kotlin.random.Random
import kotlin.random.nextInt

class JokeRepository(resource: String) {
    private val jokes = javaClass.getResourceAsStream(resource)!!.bufferedReader().readLines()
    private val random = Random(System.currentTimeMillis())

    fun provideJoke(): String =
        random.nextInt(range = jokes.indices)
            .let { jokes[it] }
}