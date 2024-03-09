package com.baeldung.nameOfEnumEntries

import org.junit.jupiter.api.Test
import kotlin.enums.EnumEntries
import kotlin.test.assertEquals

enum class CountryCode(@Suppress("UNUSED_PARAMETER")countryName: String) {
    USA("United States of America"),
    UKR("Ukraine"),
    CAN("Canada"),
    MEX("Mexico"),
    JAM("Jamaica")
}

enum class WorkingDay {
    Monday, Tuesday, Wednesday, Thursday, Friday
}

fun getNames1(enumCls: Class<out Enum<*>>) = enumCls.enumConstants.map(Enum<*>::name)

inline fun <reified T : Enum<T>> getNames2() = enumValues<T>().map { it.name }

fun <T : Enum<T>> EnumEntries<T>.names() = this.map { it.name }
fun <T : Enum<T>> Array<T>.names() = this.map { it.name }

class GetNamesOfEnumEntriesUnitTest {

    @Test
    fun `when using numConstants() then get the expected result `() {
        val names = WorkingDay::class.java.enumConstants.map { it.name }
        assertEquals(listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), names)

        val dayNames = getNames1(WorkingDay::class.java)
        assertEquals(listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), dayNames)

        val codeNames = getNames1(CountryCode::class.java)
        assertEquals(listOf("USA", "UKR", "CAN", "MEX", "JAM"), codeNames)
    }

    @Test
    fun `when using enumValues() then get the expected result `() {
        val dayNames = getNames2<WorkingDay>()
        assertEquals(listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), dayNames)

        val codeNames = getNames2<CountryCode>()
        assertEquals(listOf("USA", "UKR", "CAN", "MEX", "JAM"), codeNames)
    }

    @Test
    fun `when using values() then get the expected result `() {
        val dayNames = WorkingDay.values().names()
        assertEquals(listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), dayNames)

        val codeNames = CountryCode.values().names()
        assertEquals(listOf("USA", "UKR", "CAN", "MEX", "JAM"), codeNames)
    }

    @Test
    fun `when extending EnumEntries then get the expected result `() {
        val dayNames = WorkingDay.entries.names()
        assertEquals(listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), dayNames)

        val codeNames = CountryCode.entries.names()
        assertEquals(listOf("USA", "UKR", "CAN", "MEX", "JAM"), codeNames)
    }
}