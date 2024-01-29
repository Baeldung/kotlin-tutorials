package repeatedListElements

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RepeatedListElementsUnitTest {
    @Test
    fun `find repeated elements in a list using for loop`() {
        val list = listOf(1, 2, 3, 2, 4, 3, 5, 3, 6)

        assertEquals(listOf(2, 3), findRepeatedValuesUsingForLoop(list))
    }

    @Test
    fun `find repeated elements in a list using hashmap`() {
        val list = listOf(1, 2, 3, 2, 4, 3, 5, 3, 6)

        assertEquals(listOf(2, 3), findRepeatedValuesUsingHashMap(list))
    }

    @Test
    fun `find repeated elements in a list using groupBy method`() {
        val list = listOf(1, 2, 3, 2, 4, 3, 5, 3, 6)
        val result = list.groupBy { it }.mapValues { it.value.size }.filterValues { it > 1 }.keys.toList()

        assertEquals(listOf(2, 3), result)
    }
}
fun findRepeatedValuesUsingForLoop(list: List<Int>): List<Int> {
    val countList = MutableList(list.size) { 1 }
    for (i in 0 until list.size - 1) {
        for (j in i + 1 until list.size) {
            if (list[i] == list[j]) {
                countList[i] += 1
                countList[j] += 1
            }
        }
    }
    val repeatedValues = mutableListOf<Int>()
    for (i in 0 until list.size) {
        if (countList[i] > 1 && !repeatedValues.contains(list[i])) {
            repeatedValues.add(list[i])
        }
    }
    return repeatedValues
}
fun findRepeatedValuesUsingHashMap(list: List<Int>): List<Int> {
    val map = HashMap<Int, Int>()
    for (element in list) {
        map[element] = map.getOrDefault(element, 0) + 1
    }
    return map.filterValues { it > 1 }.keys.toList()
}
