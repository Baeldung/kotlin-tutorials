package repeatedListElements

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RepeatedListElementsUnitTest {
    @Test
    fun `find repeated elements in a list using for loop`() {
        val list = listOf(1, 2, 3, 2, 4, 3, 5, 2, 6)

        assertEquals(3, findRepeatedValuesUsingForLoop(2, list))
        assertEquals(2, findRepeatedValuesUsingForLoop(3, list))
        assertEquals(1, findRepeatedValuesUsingForLoop(4, list))
        assertEquals(0, findRepeatedValuesUsingForLoop(9, list))
    }

    @Test
    fun `find repeated elements in a list using hashmap`() {
        val list = listOf(1, 2, 3, 2, 4, 3, 5, 2, 6)

        assertEquals(3, findRepeatedValuesUsingHashMap(2, list))
        assertEquals(2, findRepeatedValuesUsingHashMap(3, list))
        assertEquals(1, findRepeatedValuesUsingHashMap(4, list))
        assertEquals(0, findRepeatedValuesUsingHashMap(9, list))
    }

    @Test
    fun `find repeated elements in a list using count method`() {
        val list = listOf(1, 2, 3, 2, 4, 3, 5, 2, 6)

        assertEquals(3, list.count{it == 2})
        assertEquals(2, list.count{it == 3})
        assertEquals(1, list.count{it == 4})
        assertEquals(0, list.count{it == 9})
    }
}
fun findRepeatedValuesUsingForLoop(value: Int, list: List<Int>): Int {
    var count = 0
    for (i in 0 until list.size - 1) {
        if (list[i] == value) {
            count ++
        }
    }

    return count
}
fun findRepeatedValuesUsingHashMap(value: Int, list: List<Int>): Int {
    val map = HashMap<Int, Int>()
    for (element in list) {
        map[element] = map.getOrDefault(element, 0) + 1
    }
    return if (!list.contains(value)) 0 else map.getValue(value)
}

