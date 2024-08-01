package com.baeldung.propertytesting

import net.jqwik.api.*
import net.jqwik.api.constraints.*
import net.jqwik.api.constraints.CharRange
import net.jqwik.api.constraints.IntRange
import net.jqwik.kotlin.api.any
import net.jqwik.kotlin.api.combine
import net.jqwik.kotlin.api.ofLength
import net.jqwik.time.api.Dates
import java.time.LocalDate
import java.time.Month
import kotlin.test.assertTrue

class Jqwik_PropertyBasedUnitTest {

    val converter = RomanNumeralsConverter()

    @Property
    fun `should generate input values`(@ForAll arg: Int) {
        println("$arg")
        assertTrue { arg is Int }
    }

    @Property(tries = 100, seed = "-1329617974163688825")
    fun `should generate input values for a given seed`(@ForAll arg: Int): Boolean {
        println("$arg")
        return arg is Int
    }

    @Property
    fun `should convert integer to roman and back`(
        @ForAll @IntRange(min = 0, max = 3999) originalInt: Int,
    ): Boolean {
        val roman = converter.intToRoman(originalInt)
        val finalInt = converter.romanToInt(roman)
        return originalInt == finalInt
    }

    @Property(tries = 4000)
    fun `should convert integer to roman and back for all valid inputs (exhaustive testing)`(
        @ForAll @IntRange(min = 0, max = 3999) originalInt: Int,
    ): Boolean {
        val roman = converter.intToRoman(originalInt)
        val finalInt = converter.romanToInt(roman)
        return originalInt == finalInt
    }

    @Report(Reporting.GENERATED)
    @Property(tries = 10)
    fun `should generate Strings`(
        @ForAll @AlphaChars @UniqueChars @NotBlank foo: String,
        @ForAll @Whitespace @CharRange(from = '!', to = '&') @StringLength(min = 3, max = 5) bar: String,
    ): Boolean {
        val fooBar = "$foo $bar"
        return fooBar.length == foo.length + bar.length + 1
    }

    @Property(tries = 10)
    fun `should generate Lists of Doubles`(
        @ForAll @NotEmpty @Size(max = 10) numbers: @UniqueElements List<Double>,
    ): Boolean {
        return numbers.size > 0
    }

    @Property
    fun `should generate Enums and Booleans`(
        @ForAll month: Month,
        @ForAll isLeapYear: Boolean?,
        @ForAll @IntRange(min = 2000, max = 2024) year: Int,
    ): Boolean {
        val display = "$year $month ${isLeapYear ?: "*"}"
        return display.isNotEmpty()
    }

    @Property
    fun `should be able to sign up`(
        @ForAll @AlphaChars @NumericChars @UniqueChars @StringLength(min = 3, max = 8) username: String,
    ): Boolean {
        return username.length >= 3
    }

    @Property
    fun `should be able to login`(@ForAll("usernames") username: String): Boolean {
        return username.length >= 3
    }

    @Property
    fun `should validate account`(@ForAll("accounts") account: Account): Boolean {
        return account.username.length >= 3
    }

    @Provide
    fun usernames(): Arbitrary<String> = String.any()
        .alpha()
        .numeric()
        .uniqueChars()
        .ofLength(3..8)

    data class Account(val id: Long, val username: String, val dateOfBirth: LocalDate)

    @Provide
    fun accounts(): Arbitrary<Account> {
        val ids = Long.any().greaterOrEqual(100)
        val datesOfBirth = Dates.dates().yearBetween(1900, 2000)
        return combine(ids, usernames(), datesOfBirth, combinator = ::Account)
    }

}
