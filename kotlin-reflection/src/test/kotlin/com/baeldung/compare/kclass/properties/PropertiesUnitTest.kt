package com.baeldung.compare.kclass.properties

import com.baeldung.compare.kclass.Weapon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class PropertiesUnitTest {
    @Test
    fun `A Kotlin Weapon class is not a Java Weapon class`() {
        assertEquals("class com.baeldung.compare.kclass.Weapon", Weapon::class.toString())
        assertEquals("class com.baeldung.compare.kclass.Weapon", Weapon::class.java.toString())

        assertNotEquals(Weapon::class, Weapon::class.java)
    }

    @Test
    fun `Using javaClass and kotlin properties for equality comparison`() {
        val sword = Weapon()
        val bow = Weapon()

        assertEquals("class com.baeldung.compare.kclass.Weapon", sword.javaClass.toString())
        assertEquals("class com.baeldung.compare.kclass.Weapon", bow.javaClass.kotlin.toString())

        assertEquals(sword.javaClass, bow.javaClass)
        assertEquals(sword.javaClass.kotlin, bow.javaClass.kotlin)
    }

    @Test
    fun `Comparing javaClass and kotlin properties for Weapon class`(){
        val sword = Weapon()
        val bow = Weapon()

        assertNotEquals(sword.javaClass, bow.javaClass.kotlin)
    }
}