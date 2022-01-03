package com.baeldung.introduction

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ListExtensionUnitTest {

    @Test
    fun givenList_whenExecuteExtensionFunctionOnList_shouldReturnRandomElementOfList() {
        //given
        val elements = listOf("a", "b", "c")

        //when
        val result = ListExtension().getRandomElementOfList(elements)

        //then
        assertTrue(elements.contains(result))
    }
}