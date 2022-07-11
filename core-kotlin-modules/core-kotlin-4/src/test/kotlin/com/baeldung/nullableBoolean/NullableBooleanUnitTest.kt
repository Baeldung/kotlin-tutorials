package com.baeldung.nullableBoolean

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


inline fun <R> getIfTrue(b: Boolean?, block: () -> R): R? {
    return if (b == true) block() else null
}

inline fun <R> getIfFalse(b: Boolean?, block: () -> R): R? {
    return if (b == false) block() else null
}

inline fun <R> getIfFalseOrNull(b: Boolean?, block: () -> R): R? {
    return if (b != true) block() else null
}

inline fun doIfTrue(b: Boolean?, block: () -> Unit) {
    if (b == true) block()
}

inline fun doIfFalse(b: Boolean?, block: () -> Unit) {
    if (b == false) block()
}

inline fun doIfFalseOrNull(b: Boolean?, block: () -> Unit) {
    if (b != true) block()
}

class NullableBooleanUnitTest {

    @Test
    fun `given nullable Boolean when calling getIf_X should get expected result`() {
        val aList = listOf("one", "two", "three")
        assertThat(getIfTrue(true) { aList[0] }).isEqualTo("one")
        assertThat(getIfTrue(false) { aList[0] }).isNull()
        assertThat(getIfTrue(null) { aList[0] }).isNull()

        assertThat(getIfFalse(true) { aList[1] }).isNull()
        assertThat(getIfFalse(false) { aList[1] }).isEqualTo("two")
        assertThat(getIfFalse(null) { aList[1] }).isNull()

        assertThat(getIfFalseOrNull(true) { aList[2] }).isNull()
        assertThat(getIfFalseOrNull(false) { aList[2] }).isEqualTo("three")
        assertThat(getIfFalseOrNull(null) { aList[2] }).isEqualTo("three")
    }

    @Test
    fun `given nullable Boolean when calling doIf_X should get expected result`() {
        val aList = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)

        doIfTrue(true) { aList[0] = -1 }; assertThat(aList[0]).isEqualTo(-1)
        doIfTrue(false) { aList[1] = -1 }; assertThat(aList[1]).isEqualTo(1)
        doIfTrue(null) { aList[2] = -1 }; assertThat(aList[2]).isEqualTo(2)

        doIfFalse(true) { aList[3] = -1 }; assertThat(aList[3]).isEqualTo(3)
        doIfFalse(false) { aList[4] = -1 }; assertThat(aList[4]).isEqualTo(-1)
        doIfFalse(null) { aList[5] = -1 }; assertThat(aList[5]).isEqualTo(5)

        doIfFalseOrNull(true) { aList[6] = -1 }; assertThat(aList[6]).isEqualTo(6)
        doIfFalseOrNull(false) { aList[7] = -1 }; assertThat(aList[7]).isEqualTo(-1)
        doIfFalseOrNull(null) { aList[8] = -1 }; assertThat(aList[8]).isEqualTo(-1)
    }
}