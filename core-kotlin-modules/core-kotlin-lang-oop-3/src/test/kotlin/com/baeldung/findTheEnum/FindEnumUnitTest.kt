package com.baeldung.findTheEnum

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

// approach 1
enum class NumberV1(val value: Int) {
    ONE(1), TWO(2), THREE(3);

    companion object {
        infix fun from(value: Int): NumberV1? = NumberV1.values().firstOrNull { it.value == value }
    }
}

// approach 2
enum class NumberV2(val value: Int) {
    ONE(1), TWO(2), THREE(3);

    companion object {
        private val map = NumberV2.values().associateBy { it.value }
        infix fun from(value: Int) = map[value]
        operator fun get(value: Int) = map[value]
    }
}

// approach 3
abstract class EnumFinder<V, E>(private val valueMap: Map<V, E>) {
    infix fun from(value: V) = valueMap[value]
}

enum class NumberV3(val value: Int) {
    ONE(1), TWO(2), THREE(3);

    companion object : EnumFinder<Int, NumberV3>(NumberV3.values().associateBy { it.value })
}

// approach 4
infix inline fun <reified E : Enum<E>, V> ((E) -> V).findBy(value: V): E? {
    return enumValues<E>().firstOrNull { this(it) == value }
}

enum class NumberV4(val value: Int) {
    ONE(1), TWO(2), THREE(3),
}

enum class OS(val input: String) {
    Linux("linux"), MacOs("mac"),
}


//Tests
class FindEnumUnitTest {

    @Test
    fun `given an enum class, when find enum by value using approach1 should find the enum or null`() {
        val searchOne = NumberV1 from 1
        assertEquals(NumberV1.ONE, searchOne)
        val searchTwo = NumberV1 from 2
        assertEquals(NumberV1.TWO, searchTwo)
        val shouldBeNull = NumberV1.from(42)
        assertNull(shouldBeNull)
    }

    @Test
    fun `given an enum class, when find enum by value using approach2 should find the enum or null`() {
        val searchOne = NumberV2 from 1
        assertEquals(NumberV2.ONE, searchOne)
        val searchTwo = NumberV2 from 2
        assertEquals(NumberV2.TWO, searchTwo)
        val shouldBeNull = NumberV2 from 42
        assertNull(shouldBeNull)

        val searchOneAgain = NumberV2[1]
        assertEquals(NumberV2.ONE, searchOneAgain)
        val searchTwoAgain = NumberV2[2]
        assertEquals(NumberV2.TWO, searchTwoAgain)
        val shouldBeNullAgain = NumberV2[42]
        assertNull(shouldBeNullAgain)
    }

    @Test
    fun `given an enum class, when find enum by value using approach3 should find the enum or null`() {
        val searchOne = NumberV3 from 1
        assertEquals(NumberV3.ONE, searchOne)
        val searchTwo = NumberV3 from 2
        assertEquals(NumberV3.TWO, searchTwo)
        val shouldBeNull = NumberV3 from 42
        assertNull(shouldBeNull)
    }

    @Test
    fun `given an enum class, when find enum by value using approach4 should find the enum or null`() {
        val searchOne = NumberV4::value findBy 1
        assertEquals(NumberV4.ONE, searchOne)
        val searchTwo = NumberV4::value findBy 2
        assertEquals(NumberV4.TWO, searchTwo)
        val shouldBeNull = NumberV4::value findBy 42
        assertNull(shouldBeNull)

        val linux = OS::input findBy "linux"
        assertEquals(OS.Linux, linux)
        val windows = OS::input findBy "windows"
        assertNull(windows)
    }
}