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
    fun `Different weapons indirectly inherit from Weapon class`() {
        val weapons = listOf(Sword(), Claymore(), Bow(), LongBow())

        assertThat(weapons).allMatch { it::class.allSuperclasses.contains(Weapon::class) }
        assertThat(weapons).allMatch { it::class.isSubclassOf(Weapon::class) }

        assertEquals(weapons, weapons.filter { it::class.allSuperclasses.contains(Weapon::class) })
    }

    @Test
    fun `Different weapons directly inherit from Weapon class`() {
        val weapons = listOf(Sword(), Claymore(), Bow(), LongBow())

        assertThat(weapons).anyMatch { it::class.superclasses.contains(Weapon::class) }

        assertNotEquals(weapons, weapons.filter { it::class.superclasses.contains(Weapon::class) })
    }

    @Test
    fun `Weapon class is a superclass of different weapons`() {
        val weapons = listOf(Sword(), Claymore(), Bow(), LongBow())

        assertThat(weapons).allMatch { Weapon::class.isSuperclassOf(it::class) }
    }
}