package com.baeldung.compare.kclass.literal

import com.baeldung.compare.kclass.Weapon
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

class LiteralUnitTest {
    @Test
    fun givenTwoWeapons_whenCheckKotlinClass_thenTheyAreEqual() {
        val sword = Weapon()
        val bow = Weapon()

        assertEquals(sword::class, bow::class)
        assertThat(sword::class).isEqualTo(bow::class)

        assertThat(sword).isInstanceOf(Weapon::class.java)
        assertInstanceOf(Weapon::class.java, sword)
    }
}