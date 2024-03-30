package com.baeldung.compare.kclass.isoperator

import com.baeldung.compare.kclass.Claymore
import com.baeldung.compare.kclass.Sword
import com.baeldung.compare.kclass.Weapon
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IsOperatorUnitTest {
    @Test
    fun givenDifferentSwords_whenCheckIsOperator_thenAllAreWeapons() {
        val weapon = Weapon()
        val sword = Sword()
        val claymore = Claymore()

        assertThat(weapon is Weapon).isTrue()
        assertThat(sword is Weapon).isTrue()
        assertThat(claymore is Weapon).isTrue()
    }
}