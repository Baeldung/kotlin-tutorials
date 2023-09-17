package com.baeldung.math.reverseNumber

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

fun reverseNumber(number: Int): Int {
    var n = number
    var result = 0
    while (n > 0) {
        result = 10 * result + n % 10
        n = n / 10
    }
    return result
}

fun Int.reverse(): Int {
    var n = this
    var result = 0
    while (n > 0) {
        result = 10 * result + n % 10
        n = n / 10
    }
    return result
}

fun Int.reverse2(): Int {
    var n = this
    var result = 0L
    while (n > 0) {
        result = 10 * result + n % 10
        require(result <= Int.MAX_VALUE) { "Cannot reverse ${this@reverse2}! Cause: Int overflow" }
        n = n / 10
    }
    return result.toInt()
}

fun Int.reverse3(): Int {
    val longNum = "$this".reversed().toLong() //asCharSeq().reverse().toStr
    require(longNum <= Int.MAX_VALUE) { "Cannot reverse $this! Cause: Int overflow" }
    return longNum.toInt()
}

class ReverseNumberUnitTest {

    @Test
    fun `when calling reverseNumber1 then the number gets reversed`() {
        val rev1 = reverseNumber(1230456)
        assertEquals(6540321, rev1)

        val rev2 = reverseNumber(10000)
        assertEquals(1, rev2)
    }

    @Test
    fun `when calling the reverse extension then the number gets reversed`() {
        val rev1 = 1234.reverse()
        assertEquals(4321, rev1)

        val rev2 = 10000.reverse()
        assertEquals(1, rev2)
    }

    @Test
    fun `when calling the reverse3 extension then overflow is handled`() {
        assertThrows<IllegalArgumentException> { 1234123409.reverse2() }.also {
            assertEquals("Cannot reverse 1234123409! Cause: Int overflow", it.message)
        }
    }

    @Test
    fun `when calling the reverse4 then the number gets reversed`() {
        val rev1 = 1234.reverse3()
        assertEquals(4321, rev1)

        val rev2 = 10000.reverse3()
        assertEquals(1, rev2)

        assertThrows<IllegalArgumentException> { 1234123409.reverse3() }.also {
            assertEquals("Cannot reverse 1234123409! Cause: Int overflow", it.message)
        }
    }
}