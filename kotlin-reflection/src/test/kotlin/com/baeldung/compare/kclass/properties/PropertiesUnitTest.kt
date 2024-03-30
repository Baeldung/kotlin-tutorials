package com.baeldung.compare.kclass.properties

import com.baeldung.compare.kclass.Weapon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class PropertiesUnitTest {
    @Test
    fun givenTwoWeapons_whenCompareKotlinClassWithJavaClass_thenTheyAreEqual() {
        assertEquals("class ro.daniel.Weapon", Weapon::class.toString())
        assertEquals("class ro.daniel.Weapon", Weapon::class.java.toString())

        assertNotEquals(Weapon::class, Weapon::class.java)
    }

    @Test
    fun givenTwoWeapons_whenCheckJavaClass_andKotlinClass_thenTheyAreEqual(){
        val sword = Weapon()
        val bow = Weapon()

        assertEquals("class ro.daniel.Weapon", sword.javaClass.toString())
        assertEquals("class ro.daniel.Weapon", bow.javaClass.kotlin.toString())

        assertEquals(sword.javaClass, bow.javaClass)
        assertEquals(sword.javaClass.kotlin, bow.javaClass.kotlin)
    }

    @Test
    fun givenTwoWeapons_whenCompareJavaClassWithKotlinClass_thenTheyAreNotEqual(){
        val sword = Weapon()
        val bow = Weapon()

        assertNotEquals(sword.javaClass, bow.javaClass.kotlin)
    }
}