package com.baeldung.kluent

import org.amshove.kluent.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class EquivalencyUnitTest {
    @Test
    fun `when asserting equality then the equals method should be used`() {
        val actual = NotAllFieldsChecked("123", "Baeldung", 42)

        actual shouldBeEqualTo NotAllFieldsChecked("123", "Other", 100)
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    @Disabled // We expect this to fail
    fun `when asserting equivalency then the individual fields should be checked`() {
        val actual = NotAllFieldsChecked("123", "Baeldung", 42)

        actual.shouldBeEquivalentTo(NotAllFieldsChecked("123", "Other", 100))
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `when asserting equivalency specifying which fields to exclude then only the other fields should be checked`() {
        val actual = NotAllFieldsChecked("123", "Baeldung", 42)

        actual.shouldBeEquivalentTo(NotAllFieldsChecked("123", "Other", 42)) {
            it.excluding(NotAllFieldsChecked::name)
        }
    }

    class NotAllFieldsChecked(val id: String, val name: String, val age: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as NotAllFieldsChecked

            return id == other.id
        }

        override fun hashCode(): Int {
            return id.hashCode()
        }
    }
}