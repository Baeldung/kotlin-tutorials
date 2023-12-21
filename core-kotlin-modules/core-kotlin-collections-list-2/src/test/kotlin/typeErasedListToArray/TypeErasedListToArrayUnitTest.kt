package typeErasedListToArray

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class typeErasedListToArrayUnitTest {

    @Test
    fun `convert type-erased list to array using toTypedArray`() {
        val intList: List<Any> = listOf(1, 2, 3, 4, 5)
        val stringList: List<Any> = listOf("one", "two", "three", "four", "five")

        val array1 = intList.toTypedArray()
        val array2 = stringList.toTypedArray()

        assertArrayEquals(arrayOf(1, 2, 3, 4, 5), array1)
        assertArrayEquals(arrayOf("one", "two", "three", "four", "five"), array2)
    }

    @Test
    fun `convert type-erased list to array using toTypedArray with reified and inline`() {
        val intList: List<Any> = listOf(1, 2, 3, 4, 5)
        val stringList: List<Any> = listOf("one", "two", "three", "four", "five")

        val array1 = mapToArray<Int>(intList)
        val array2 = mapToArray<String>(stringList)

        assertArrayEquals(arrayOf(1, 2, 3, 4, 5), array1)
        assertArrayEquals(arrayOf("one", "two", "three", "four", "five"), array2)
    }

    @Test
    fun `convert type-erased list to array using array constructor`() {
        val intList: List<Any> = listOf(1, 2, 3, 4, 5)
        val stringList: List<Any> = listOf("one", "two", "three", "four", "five")

        var array1 = Array(intList.size) { i -> intList[i] as Int}
        val array2 = Array(stringList.size) { i -> stringList[i] as String}

        assertArrayEquals(arrayOf(1, 2, 3, 4, 5), array1)
        assertArrayEquals(arrayOf("one", "two", "three", "four", "five"), array2)
    }

    inline fun <reified T> mapToArray(list: List<*>): Array<T> {
        return list.mapNotNull{ it as? T }.toTypedArray()
    }
}