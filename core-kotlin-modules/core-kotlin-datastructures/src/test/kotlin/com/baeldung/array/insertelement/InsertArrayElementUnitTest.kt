package com.baeldung.array.insertelement

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class InsertArrayElementUnitTest {

    @Test
    fun `insert array element at specific position using a loop and new array`(){
        val array = arrayOf("one", "three", "four", "five")

        val position = 1
        val element = "two"
        val newArray = Array(array.size + 1) { "" }
        for (i in 0 until newArray.size) {
            if (i < position) {
                newArray[i] = array[i]
            } else if (i == position) {
                newArray[i] = element
            } else {
                newArray[i] = array[i - 1]
            }
        }
        assertArrayEquals(arrayOf("one", "two","three", "four", "five"), newArray)
    }

    @Test
    fun `insert array element at specific position using system arraycopy() and new array`(){
        val array = arrayOf("one", "three", "four", "five")
        val position = 1
        val element = "two"
        val newArray = Array(array.size + 1) { "" }

        System.arraycopy(array, 0, newArray, 0, position)
        newArray[position] = element
        System.arraycopy(array, position, newArray, position + 1, array.size - position)

        assertArrayEquals(arrayOf("one", "two","three", "four", "five"), newArray)
    }

    @Test
    fun `insert array element at specific position using copyOfRange() method`(){
        val array = arrayOf("one", "three", "four", "five")
        val position = 1
        val element = "two"
        val newArray = array.copyOfRange(0, position) + element + array.copyOfRange(position, array.size)

        assertArrayEquals(arrayOf("one", "two","three", "four", "five"), newArray)
    }

    @Test
    fun `insert array element at specific position using set method`() {
        val array = arrayOf("one", "three", "four", "five")
        val position = 1
        val element = "two"
        array.set(position, element)

        assertArrayEquals(arrayOf("one", "two","four", "five"), array)
    }

    @Test
    fun `insert array element at specific position using slice() and plus operator`() {
        val array = arrayOf("one", "three", "four", "five")
        val position = 1
        val element = "two"
        val newArray = insertArrayElementUsingSliceMethodAndPlusOperator(array, position, element)

        assertArrayEquals(arrayOf("one", "two", "three","four", "five"), newArray)
    }

    @Test
    fun `insert array element at specific position using mutabelist method`() {
        val array = arrayOf("one", "three", "four", "five")
        val position = 1
        val element = "two"
        val newArray = insertArrayElementUsingMutableList(array, position, element)

        assertArrayEquals(arrayOf("one", "two", "three","four", "five"), newArray)
    }

    @Test
    fun `insert array element at specific position using arraycopyof method`() {
        val array = arrayOf("one", "three", "four", "five")
        val position = 1
        val element = "two"
        val newArray = insertArrayElementUsingArrayCopyOfMethod(array, position, element)

        assertArrayEquals(arrayOf("one", "two", "three","four", "five"), newArray)
    }

    @Test
    fun `insert array element at specific position using map method`() {
        val array = arrayOf("one", "three", "four", "five")
        val position = 1
        val element = "two"
        val newArray = insertArrayElementUsingMap(array, position, element)

        assertArrayEquals(arrayOf("one", "two", "three","four", "five"), newArray)
    }
}
fun insertArrayElementUsingSliceMethodAndPlusOperator(array: Array<String>, position: Int, element: String): Array<String> {
    return array.sliceArray(0 until position) +
            element + array.sliceArray(position until array.size)
}
fun insertArrayElementUsingMutableList(array: Array<String>, position: Int, element: String): Array<String> {
    val mutableList = array.toMutableList()
    mutableList.add(position, element)
    return mutableList.toTypedArray()
}
fun insertArrayElementUsingArrayCopyOfMethod(array: Array<String>, position: Int, element: String): Array<String?> {
    val newArray = array.copyOf(array.size + 1)
    for (i in newArray.size - 1 downTo position + 1) {
        newArray[i] = newArray[i - 1]
    }
    newArray[position] = element

    return newArray
}
fun insertArrayElementUsingMap(array: Array<String>, position: Int, element: String): Array<String> {
    return array.mapIndexed { index, currenElement ->
        if (index == position) {
            listOf(element, currenElement)
        } else {
            listOf(currenElement)
        }
    }.flatten().toTypedArray()
}