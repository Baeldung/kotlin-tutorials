package com.baeldung.kotlin.immutable

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableSet
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class ReadOnlyUnitTest{

    @Test
    fun givenReadOnlyList_whenCastToMutableList_checkNewElementsAdded(){

        val list: List<String> = listOf("This", "Is", "Totally", "Immutable")

        (list as MutableList<String>)[2] = "Not"

        assertEquals(listOf("This", "Is", "Not", "Immutable"), list)

    }

    @Test
    fun givenImmutableList_whenAddTried_checkExceptionThrown(){

        assertThrows<UnsupportedOperationException> {
            ImmutableList.of("I", "am", "actually", "immutable")
        }

        val list: List<String> = listOf("I", "am", "actually", "immutable")
        (list as MutableList<String>).add("Oops")

    }

    @Test
    fun givenMutableList_whenCopiedAndAddTried_checkExceptionThrown(){

        val mutableList : List<String> = listOf("I", "Am", "Definitely", "Immutable")

        (mutableList as MutableList<String>)[2] = "100% Not"

        assertEquals(listOf("I", "Am", "100% Not", "Immutable"), mutableList)

        assertThrows<UnsupportedOperationException>{
            ImmutableList.copyOf(mutableList)
        }

        val list: List<String> = listOf("I", "Am", "Definitely", "Mutable")
        (list as MutableList<String>)[2] = "Really?"

    }

    @Test
    fun givenImmutableSetBuilder_whenAddTried_checkExceptionThrown(){

        val mutableList : List<String> = listOf("Hello", "Baeldung")
        val set: ImmutableSet<String> = ImmutableSet.builder<String>()
                .add("I","am","immutable")
                .addAll(mutableList)
                .build()

        assertEquals(setOf("Hello", "Baeldung", "I", "am", "immutable"), set)

        (set as MutableSet<String>).add("Oops")

        assertThrows<UnsupportedOperationException> {
            ImmutableSet.builder<String>()
                .add("I","am","immutable")
                .addAll(mutableList)
                .build()
        }
    }
}