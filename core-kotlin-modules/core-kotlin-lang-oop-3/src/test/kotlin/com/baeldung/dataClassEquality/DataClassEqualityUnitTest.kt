package com.baeldung.dataClassEquality

import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertFalse
import kotlin.test.assertTrue


data class PersonV1(val firstname: String, val lastname: String) {
    lateinit var dateOfBirth: LocalDate
}

data class PersonV2(val firstname: String, val lastname: String) {
    lateinit var dateOfBirth: LocalDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonV2) return false

        if (firstname != other.firstname) return false
        if (lastname != other.lastname) return false
        if (dateOfBirth != other.dateOfBirth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstname.hashCode()
        result = 31 * result + lastname.hashCode()
        result = 31 * result + dateOfBirth.hashCode()
        return result
    }

}


data class BaeldungString(val value: String, val chars: CharArray)

data class BaeldungStringV2(val value: String, val chars: CharArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaeldungStringV2) return false

        if (value != other.value) return false
        if (!chars.contentEquals(other.chars)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + chars.contentHashCode()
        return result
    }
}

class DataClassEqualityUnitTest {

    @Test
    fun `given two PersonV1 objects with different addresses when compare with equals should get true`() {
        val p1 = PersonV1("Amanda", "Smith").also { it.dateOfBirth = LocalDate.of(1992, 8, 8) }
        val p2 = PersonV1("Amanda", "Smith").also { it.dateOfBirth = LocalDate.of(1976, 11, 18) }
        assertTrue { p1 == p2 }
    }

    @Test
    fun `given two PersonV2 objects with different addresses when compare with equals should get false`() {
        val p1 = PersonV2("Amanda", "Smith").also { it.dateOfBirth = LocalDate.of(1992, 8, 8) }
        val p2 = PersonV2("Amanda", "Smith").also { it.dateOfBirth = LocalDate.of(1976, 11, 18) }
        assertFalse { p1 == p2 }
    }

    @Test
    fun `given two BaeldungString objects with the same values in the array when compare with equals should get false`() {
        val s1 = BaeldungString("Amanda", charArrayOf('A', 'm', 'a', 'n', 'd', 'a'))
        val s2 = BaeldungString("Amanda", charArrayOf('A', 'm', 'a', 'n', 'd', 'a'))
        assertFalse { s1 == s2 }
    }

    @Test
    fun `given two arrays and lists when compare with equals should behave differently`() {
        val list1 = listOf("one", "two", "three", "four")
        val list2 = listOf("one", "two", "three", "four")

        val array1 = arrayOf("one", "two", "three", "four")
        val array2 = arrayOf("one", "two", "three", "four")

        assertTrue { list1 == list2 }
        assertFalse { array1 == array2 }
        assertTrue { array1 contentEquals array2 }
    }

    @Test
    fun `given two BaeldungStringV2 objects with the same values in the array when compare with equals should get true`() {
        val s1 = BaeldungStringV2("Amanda", charArrayOf('A', 'm', 'a', 'n', 'd', 'a'))
        val s2 = BaeldungStringV2("Amanda", charArrayOf('A', 'm', 'a', 'n', 'd', 'a'))
        assertTrue { s1 == s2 }
    }

}