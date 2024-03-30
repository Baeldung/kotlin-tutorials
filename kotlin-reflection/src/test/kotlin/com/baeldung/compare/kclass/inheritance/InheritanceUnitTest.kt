package com.baeldung.compare.kclass.inheritance

import com.baeldung.compare.kclass.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.full.superclasses

class InheritanceUnitTest {
    @Test
    fun givenListOfWeapons_whenCheckAllSuperclasses_andSubclassOfWeapon_thenAllAreWeapons() {
        val weapons = listOf(Sword(), Claymore(), Bow(), LongBow())

        assertThat(weapons).allMatch { it::class.allSuperclasses.contains(Weapon::class) }
        assertThat(weapons).allMatch { it::class.isSubclassOf(Weapon::class) }

        assertEquals(weapons, weapons.filter { it::class.allSuperclasses.contains(Weapon::class) })
    }

    @Test
    fun givenListOfWeapons_whenCheckSuperclasses_thenSomeAreWeapons() {
        val weapons = listOf(Sword(), Claymore(), Bow(), LongBow())

        assertThat(weapons).anyMatch { it::class.superclasses.contains(Weapon::class) }

        assertNotEquals(weapons, weapons.filter { it::class.superclasses.contains(Weapon::class) })
    }

    @Test
    fun givenListOfWeapons_whenCheckWeaponIsSuperclass_thenAllAreSubclassOfWeapon() {
        val weapons = listOf(Sword(), Claymore(), Bow(), LongBow())

        assertThat(weapons).allMatch { Weapon::class.isSuperclassOf(it::class) }
    }
}