package com.baeldung.multiVarLet

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

inline fun <T1 : Any, T2 : Any, R : Any> let2(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> let3(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3) -> R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}


inline fun <T : Any, R : Any> letIfAllNotNull(vararg arguments: T?, block: (List<T>) -> R): R? {
    return if (arguments.all { it != null }) {
        block(arguments.filterNotNull())
    } else null
}

inline fun <T : Any, R : Any> letIfAnyNotNull(vararg arguments: T?, block: (List<T>) -> R?): R? {
    return if (arguments.any { it != null }) {
        block(arguments.filterNotNull())
    } else null
}

class MultipleVariablesLetUnitTest {
    private val nullNum: Int? = null

    @Test
    fun `given two nullable variables when run let on them should get expected result`() {
        val theName: String? = "Kai"
        val theNumber: Int? = 7
        val result = theName?.let { name ->
            theNumber?.let { num -> "Hi $name, $num squared is ${num * num}" }
        }
        assertThat(result).isEqualTo("Hi Kai, 7 squared is 49")
    }

    @Test
    fun `given two nullable variables when calling let2 on them should get expected result`() {
        assertThat(let2("Kai", 7) { name, num -> "Hi $name, $num squared is ${num * num}" }).isEqualTo("Hi Kai, 7 squared is 49")

        assertThat(let2(nullNum, 7) { name, num -> "Hi $name, $num squared is ${num * num}" }).isNull()
        assertThat(let2(7, nullNum) { name, num -> "Hi $name, $num squared is ${num * num}" }).isNull()
        assertThat(let2(nullNum, nullNum) { name, num -> "Hi $name, $num squared is ${num * num}" }).isNull()
    }

    @Test
    fun `given three nullable variables when calling let3 on them should get expected result`() {
        assertThat(let3(5, 6, 7) { n1, n2, n3 -> "$n1 + $n2 + $n3 is ${n1 + n2 + n3}" }).isEqualTo("5 + 6 + 7 is 18")

        assertThat(let3(nullNum, 7, 6) { n1, n2, n3 -> "$n1 + $n2 + $n3 is ${n1 + n2 + n3}" }).isNull()
        assertThat(let3(nullNum, nullNum, 6) { n1, n2, n3 -> "$n1 + $n2 + $n3 is ${n1 + n2 + n3}" }).isNull()
        assertThat(let3(nullNum, nullNum, nullNum) { n1, n2, n3 -> "$n1 + $n2 + $n3 is ${n1 + n2 + n3}" }).isNull()
    }

    @Test
    fun `given three nullable variables when calling whenAllNotNullLet on them should get expected result`() {
        assertThat(letIfAllNotNull(5, 6, 7, 8) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isEqualTo("5 + 6 + 7 + 8 is 26")
        assertThat(letIfAllNotNull(5, 6, 7) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isEqualTo("5 + 6 + 7 is 18")
        assertThat(letIfAllNotNull(5, 7) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isEqualTo("5 + 7 is 12")

        assertThat(letIfAllNotNull(nullNum, 7, 6) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isNull()
        assertThat(letIfAllNotNull(nullNum, null, 6) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isNull()
        assertThat(letIfAllNotNull(nullNum, nullNum, nullNum) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isNull()
    }

    @Test
    fun `given three nullable variables when calling whenAnyNotNullLet on them should get expected result`() {
        assertThat(letIfAnyNotNull(5, 6, 7) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isEqualTo("5 + 6 + 7 is 18")

        assertThat(letIfAnyNotNull(nullNum, 6, 7) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isEqualTo("6 + 7 is 13")
        assertThat(letIfAnyNotNull(nullNum, nullNum, 7) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isEqualTo("7 is 7")
        assertThat(letIfAnyNotNull(nullNum, nullNum, nullNum) { "${it.joinToString(separator = " + ") { num -> "$num" }} is ${it.sum()}" }).isNull()
    }
}