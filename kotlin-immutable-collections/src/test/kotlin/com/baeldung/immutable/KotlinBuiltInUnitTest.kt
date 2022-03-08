package com.baeldung.immutable

import org.junit.Test
import uy.klutter.core.collections.asReadOnly
import uy.klutter.core.collections.toImmutable
import java.util.*

class KotlinBuiltInUnitTest {

    @Test
    fun `Kotlin built-in immutable collections`() {
        val mutableList = mutableListOf<String>()

        mutableList.add("Hello")

        // Prints "Hello"
        println(mutableList.joinToString())

        // Makes a copy
        val immutableList: List<String> = mutableList.toList()

        // Error - Immutable List does not have addition methods
        // immutableList[0] = "World"

        // Makes a copy
        val backToMutableList = immutableList.toMutableList()

        // Modifies a copy
        backToMutableList[0] = "World"

        // Prints "World"
        println(backToMutableList.joinToString())
        // Prints "Hello"
        println(mutableList.joinToString())
    }

    @Test
    fun `Casting to immutable collections - issue`() {
        val mutableList = mutableListOf<String>()

        mutableList.add("Hello")

        // Prints "Hello"
        println(mutableList.joinToString())

        // Just casting
        val immutableList: List<String> = mutableList

        // Error - List does not have addition methods
        // immutableList[0] = "World"

        // Unsafe casting
        val backToMutableList = immutableList as MutableList<String>

        // Modifies original collection
        backToMutableList[0] = "World"

        // Prints "World"
        println(backToMutableList.joinToString())
        // Prints "World"
        println(mutableList.joinToString())
    }

    @Test
    fun `User-safe immutable approach - Delegate approach`() {
        val mutableList = mutableListOf<String>()

        mutableList.add("Hello")

        // Prints "Hello"
        println(mutableList.joinToString())

        // Wrap - no copy!
        val immutableList = ImmutableList(mutableList)

        // Error - Immutable List does not have addition methods
        // immutableList[0] = "World"

        // Error - Cannot cast to Mutable list
        // val backToMutable = immutableList as MutableList<String>
    }

    @Test
    fun `Delegate approach - Klutter`() {
        val map = mutableMapOf<String, String>()
        val set = mutableSetOf<String>()

        // Copies and wraps with delegate
        map.toImmutable()
        // Just wraps with delegate
        set.asReadOnly()
    }


}
