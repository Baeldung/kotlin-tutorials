package com.baeldung.casting

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class TreatDispenserUnitTest {

    @Test
    fun `dispenseTreat should return the first treat`() {

        val dispenser = TreatDispenser<Candy>()
        dispenser.addTreat(ChocolateBar)
        dispenser.addTreat(Lollipop)

        assertEquals(ChocolateBar, dispenser.dispenseTreat())
        assertEquals(Lollipop, dispenser.dispenseTreat())
        assertNull(dispenser.dispenseTreat())
    }

    @Test
    fun `peekNextTreat should show the next treat without removing it`() {
        val dispenser = TreatDispenser<Candy>()
        dispenser.addTreat(Lollipop)

        assertEquals(Lollipop, dispenser.peekNextTreat())
        assertEquals(Lollipop, dispenser.peekNextTreat())
    }

    @Test
    fun `peekAtNextTreat using star projection works for all types`() {
        val candyDispenser = TreatDispenser<Candy>()
        candyDispenser.addTreat(ChocolateBar)

        val trinketDispenser = TreatDispenser<SpookyTrinket>()
        trinketDispenser.addTreat(VampireFang)
        trinketDispenser.addTreat(FakeSpider)

        // Test with Candy dispenser
        assertDoesNotThrow {
            peekAtNextTreat(candyDispenser)
        }

        // Test with Trinket dispenser
        assertDoesNotThrow {
            peekAtNextTreat(trinketDispenser)
        }
    }

    @Test
    fun `peekAtNextTreatAny fails for non-Any dispensers`() {
        val candyDispenser = TreatDispenser<Candy>()
        candyDispenser.addTreat(ChocolateBar)

        // This would fail type checking, hence commented:
        // peekAtNextTreatAny(candyDispenser) // Error: Type mismatch

        val anyDispenser = TreatDispenser<Any>()
        anyDispenser.addTreat("Surprise Treat")

        assertDoesNotThrow {
            peekAtNextTreatAny(anyDispenser)
        }
    }

}
