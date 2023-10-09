package com.baeldung.frequencymap

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class FrequencyMapUnitTest {

    @Test
    fun `test frequency map using mutable map method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        assertEquals(expectedMap, frequencyMapUsingMutableMap(list))
    }

    @Test
    fun `test frequency map using groupingBy() method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = list.groupingBy { it }.eachCount()
        assertEquals(expectedMap, actualMap)
    }

    @Test
    fun `test frequency map using frequency() method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = mutableMapOf<Int, Int>()
        for(value in list.distinct()){
            actualMap[value] = Collections.frequency(list, value)
        }
        assertEquals(expectedMap, actualMap)
    }

    @Test
    fun `test frequency map using treemap method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = frequencyMapUsingTreeMapMethod(list)

        assertEquals(expectedMap, actualMap)
    }


    @Test
    fun `test frequency map using sequence method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = list.asSequence().groupingBy { it }.eachCount()

        assertEquals(expectedMap, actualMap)
    }

    @Test
    fun `test frequency map using sorted set method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = frequencyMapUsingSortedSetMethod(list)

        assertEquals(expectedMap, actualMap)
    }

    @Test
    fun `test frequency map using bitset method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = frequencyMapUsingTreeBitSetMethod(list)

        assertEquals(expectedMap, actualMap)
    }

    @Test
    fun `test frequency map using binary search method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = frequencyMapUsingBinarySearchMethod(list)

        assertEquals(expectedMap, actualMap)
    }

    @Test
    fun `test frequency map using hashset method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = FrequencyMapUsingHashSetMethod(list)

        assertEquals(expectedMap, actualMap)
    }

    @Test
    fun `test frequency map using merge() method`() {
        val list = listOf(1, 2, 1, 3, 2, 4, 4, 7,9,7,3,2,1)
        val expectedMap = mapOf(1 to 3, 2 to 3, 3 to 2, 4 to 2, 7 to 2, 9 to 1)
        val actualMap = FrequencyMapUsingReduceMethod(list)

        assertEquals(expectedMap, actualMap)
    }
}
fun frequencyMapUsingMutableMap(list: List<Int>): MutableMap<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    for (value in list) {
        val count = map.getOrDefault(value, 0)
        map[value] = count + 1
    }
    return map
}
fun frequencyMapUsingTreeMapMethod(list: List<Int>): MutableMap<Int, Int>{
    val map = TreeMap<Int, Int>()

    list.forEach { element ->
        map[element] = map.getOrDefault(element, 0) + 1
    }

    return map
}
fun frequencyMapUsingSortedSetMethod(list: List<Int>): MutableMap<Int, Int>{
    val set = list.toSortedSet()

    val map = mutableMapOf<Int, Int>()

    set.forEach { element ->
        map[element] = list.count { it == element }
    }

    return map
}
fun frequencyMapUsingTreeBitSetMethod(list: List<Int>): MutableMap<Int, Int>{
    val bitset = BitSet(list.size)

    val map = mutableMapOf<Int, Int>()

    list.forEachIndexed { index, element ->
        if (bitset.get(index)) {
            return@forEachIndexed
        }

        val freq = list.count { it == element }
        bitset.set(index, true)

        map[element] = freq
    }

    return map
}
fun frequencyMapUsingBinarySearchMethod(list: List<Int>): MutableMap<Int, Int>{
    val sortedList = list.sorted()

    val map = mutableMapOf<Int, Int>()

    sortedList.forEach { element ->
        val firstIndex = sortedList.indexOfFirst { it == element }
        val lastIndex = sortedList.indexOfLast { it == element }

        val freq = lastIndex - firstIndex + 1

        map[element] = freq
    }

    return map
}
fun FrequencyMapUsingHashSetMethod(list: List<Int>): Map<Int, Int> {
    val set = HashSet(list)
    val map = mutableMapOf<Int, Int>()
    for (elem in set) {
        map[elem] = Collections.frequency(list, elem)
    }
    return map
}
fun FrequencyMapUsingReduceMethod(list: List<Int>): Map<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    for (element in list) {
        map.merge(element, 1) {  oldValue, _ -> oldValue + 1 }
    }

    return map
}