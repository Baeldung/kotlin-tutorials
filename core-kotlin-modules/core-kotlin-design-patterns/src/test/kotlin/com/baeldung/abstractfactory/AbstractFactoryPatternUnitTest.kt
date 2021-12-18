package com.baeldung.abstractfactory

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class AbstractFactoryPatternUnitTest {

    @Test
    fun givenCrossbowFactory_whenBuildWeapon_thenCrossbowWeaponIsReturned() {

        val factory: WeaponFactory = Crossbow.Factory

        val crossbow = factory.buildWeapon()

        assertNotNull(crossbow)
        assertEquals("Using crossbow weapon", crossbow.use())
    }

    @Test
    fun givenKatanaFactory_whenBuildWeapon_thenKatanaWeaponIsReturned() {

        val factory: WeaponFactory = Katana.Factory

        val katana = factory.buildWeapon()

        assertNotNull(katana)
        assertEquals("Using katana weapon", katana.use())
    }
}