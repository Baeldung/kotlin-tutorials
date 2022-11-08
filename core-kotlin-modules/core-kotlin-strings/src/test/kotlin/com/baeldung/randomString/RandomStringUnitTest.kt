package com.baeldung.randomString

import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Test
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random
import kotlin.streams.asSequence
import kotlin.test.assertTrue

const val STRING_LENGTH = 10

val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun randomStringByJavaRandom() = ThreadLocalRandom.current()
    .ints(STRING_LENGTH.toLong(), 0, charPool.size)
    .asSequence()
    .map(charPool::get)
    .joinToString("")

fun randomStringByKotlinRandom() = (1..STRING_LENGTH)
    .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
    .joinToString("")

fun randomStringByKotlinCollectionRandom() = List(STRING_LENGTH) { charPool.random() }.joinToString("")
fun randomStringByApacheCommons() = RandomStringUtils.randomAlphanumeric(STRING_LENGTH)

class RandomStringUnitTest {

    @Test
    fun givenAStringLength_whenUsingJava_thenReturnAlphanumericString() {
        verifyTheSolution { randomStringByJavaRandom() }
    }

    @Test
    fun givenAStringLength_whenUsingKotlin_thenReturnAlphanumericString() {
        verifyTheSolution { randomStringByKotlinRandom() }
    }

    @Test
    fun givenAStringLength_whenUsingKotlinCollectionRandom_thenReturnAlphanumericString() {
        verifyTheSolution { randomStringByKotlinCollectionRandom() }
    }

    @Test
    fun givenAStringLength_whenUsingApacheCommon_thenReturnAlphanumericString() {
        verifyTheSolution { randomStringByApacheCommons() }
    }

    private fun verifyTheSolution(randomFunc: () -> String) {
        val randoms = List(10_000) { randomFunc() }

        assertTrue { randoms.all { it.matches(Regex("^[0-9a-zA-z]+$")) } }
        assertTrue { randoms.none { it.length != STRING_LENGTH } }
        assertTrue { randoms.size == randoms.toSet().size } //no duplicate
    }
}