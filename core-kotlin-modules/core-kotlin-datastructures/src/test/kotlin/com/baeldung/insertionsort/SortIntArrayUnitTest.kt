import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

fun insertionSort(arr: IntArray) {
    val n = arr.size
    for (i in 1 until n) {
        val key = arr[i]
        var j = i - 1
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        arr[j + 1] = key
    }
}

class SortIntArrayUnitTest {
    @Test
    fun testInsertionSort() {
        val myArray = intArrayOf(5, 2, 9, 3, 6)
        insertionSort(myArray)
        val expectedSortedArray = intArrayOf(2, 3, 5, 6, 9)
        assertArrayEquals(expectedSortedArray, myArray)
    }
}