package com.baeldung.extensionOfEnum

import org.junit.jupiter.api.Test
import kotlin.enums.EnumEntries
import kotlin.test.assertEquals

enum class Level {
    A, B, C, D, E
}

enum class WorkingDay(val n: Int) {
    MON(1), TUE(2), WED(3), THU(4), FRI(5);
}


class EnumExtensionFunctionsUnitTest {

    @Test
    fun `when extending enum Array, then get expected result`() {
        fun <E : Enum<E>> Array<E>.joinTheirNames(): String {
            return joinToString { it.name }
        }

        assertEquals("A, B, C, D, E", Level.values().joinTheirNames())
        assertEquals("MON, TUE, WED, THU, FRI", WorkingDay.values().joinTheirNames())
    }

    @Test
    fun `when extending EnumEntries, then get expected result`() {
        fun EnumEntries<*>.joinTheirNames(): String {
            return joinToString { it.name }
        }

        assertEquals("A, B, C, D, E", Level.entries.joinTheirNames())
        assertEquals("MON, TUE, WED, THU, FRI", WorkingDay.entries.joinTheirNames())
    }


    inline fun <reified E : Enum<E>> Enum.Companion.joinTheirNames(): String {
        return enumValues<E>().joinToString { it.name }
    }

    @Test
    fun `when extending Enum's Companion object, then get expected result`() {
        assertEquals("A, B, C, D, E", Enum.joinTheirNames<Level>())
        assertEquals("MON, TUE, WED, THU, FRI", Enum.joinTheirNames<WorkingDay>())
    }

    inline fun <reified E : Enum<E>> joinEnumNames(): String {
        return enumValues<E>().joinToString { it.name }
    }

    @Test
    fun `when using a generic function, then get expected result`() {
        assertEquals("A, B, C, D, E", joinEnumNames<Level>())
        assertEquals("MON, TUE, WED, THU, FRI", joinEnumNames<WorkingDay>())
    }

}