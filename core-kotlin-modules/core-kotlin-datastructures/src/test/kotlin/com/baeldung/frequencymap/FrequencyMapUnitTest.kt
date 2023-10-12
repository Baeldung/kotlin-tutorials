package com.baeldung.frequencymap

import org.junit.Test
import java.util.*
import kotlin.test.assertContentEquals
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
        val sortedList = list.sorted().distinct()

        assertEquals(expectedMap, actualMap)
        assertEquals(sortedList, actualMap.keys.toList())
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
        val actualMap = FrequencyMapUsingMergeMethod(list)

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

fun frequencyMapUsingBinarySearchMethod(list: List<Int>): MutableMap<Int, Int>{
    val sortedList = list.sorted()

    val map = mutableMapOf<Int, Int>()

    sortedList.distinct().forEach { element ->
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
fun FrequencyMapUsingMergeMethod(list: List<Int>): Map<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    for (element in list) {
        map.merge(element, 1) {  oldValue, _ -> oldValue + 1 }
    }

    return map
}