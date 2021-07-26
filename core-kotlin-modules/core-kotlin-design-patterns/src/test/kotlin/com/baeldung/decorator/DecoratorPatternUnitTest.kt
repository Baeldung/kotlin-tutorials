package com.baeldung.decorator

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class DecoratorPatternUnitTest {

    @Test
    fun christmasTreeWithBubbleLights() {

        val christmasTree = BubbleLights(PineChristmasTree())
        val decoratedChristmasTree = christmasTree.decorate()

        assertNotNull(decoratedChristmasTree)
        assertEquals("Pine christmas tree with Bubble Lights", decoratedChristmasTree)
    }

    @Test
    fun christmasTreeWithTinsel() {

        val christmasTree = Tinsel(PineChristmasTree())
        val decoratedChristmasTree = christmasTree.decorate()

        assertNotNull(decoratedChristmasTree)
        assertEquals("Pine christmas tree with Tinsel", decoratedChristmasTree)
    }

    @Test
    fun christmasTreeWithGarlands() {

        val christmasTree = Garlands(PineChristmasTree())
        val decoratedChristmasTree = christmasTree.decorate()

        assertNotNull(decoratedChristmasTree)
        assertEquals("Pine christmas tree with Garlands", decoratedChristmasTree)
    }

    @Test
    fun christmasTreeWithBubbleLightsAndGarlands() {

        val christmasTree = Garlands(BubbleLights(PineChristmasTree()))
        val decoratedChristmasTree = christmasTree.decorate()

        assertNotNull(decoratedChristmasTree)
        assertEquals("Pine christmas tree with Bubble Lights with Garlands", decoratedChristmasTree)
    }

}