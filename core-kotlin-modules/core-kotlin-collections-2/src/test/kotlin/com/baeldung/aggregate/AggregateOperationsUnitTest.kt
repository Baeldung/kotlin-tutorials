package com.baeldung.aggregate

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

@ExperimentalUnsignedTypes
class AggregateOperationsUnitTest {

    private val classUnderTest: AggregateOperations = AggregateOperations()

    @Test
    fun whenCountOfList_thenReturnsValue() {
        assertEquals(4, classUnderTest.countList())
    }

    @Test
    fun whenSumOfList_thenReturnsTotalValue() {
        assertEquals(27, classUnderTest.sumList())
    }

    @Test
    fun whenSumOf_thenShouldSumAndPreserveType() {
        val employees = listOf(Employee("A", 3500, 23u), Employee("B", 2000, 30u))

        assertEquals(5500, employees.sumOf { it.salary })
        assertEquals(53u, employees.sumOf { it.age })
    }

    @Test
    fun whenAverageOfList_thenReturnsValue() {
        assertEquals(6.75, classUnderTest.averageList())
    }

    @Test
    fun whenMaximumOfList_thenReturnsMaximumValue() {
        assertEquals(15, classUnderTest.maximumInList())
    }

    @Test
    fun whenMinimumOfList_thenReturnsMinimumValue() {
        assertEquals(1, classUnderTest.minimumInList())
    }

    @Test
    fun whenMaxByList_thenReturnsLargestValue() {
        assertEquals(3, classUnderTest.maximumByList())
    }

    @Test
    fun whenMinByList_thenReturnsSmallestValue() {
        assertEquals(15, classUnderTest.minimumByList())
    }

    @Test
    fun whenMaxWithList_thenReturnsLargestValue(){
        assertEquals("Kolkata", classUnderTest.maximumWithList())
    }

    @Test
    fun whenMinWithList_thenReturnsSmallestValue(){
        assertEquals("Barcelona", classUnderTest.minimumWithList())
    }

    @Test
    fun whenSumByList_thenReturnsIntegerValue(){
        assertEquals(135, classUnderTest.sumByList())
    }

    @Test
    fun whenSumByDoubleList_thenReturnsDoubleValue(){
        assertEquals(3.375, classUnderTest.sumByDoubleList())
    }

    @Test
    fun whenFoldList_thenReturnsValue(){
        assertEquals(73, classUnderTest.foldList())
    }

    @Test
    fun whenFoldRightList_thenReturnsValue(){
        assertEquals(73, classUnderTest.foldRightList())
    }

    @Test
    fun whenFoldIndexedList_thenReturnsValue(){
        assertEquals(89, classUnderTest.foldIndexedList())
    }

    @Test
    fun whenFoldRightIndexedList_thenReturnsValue(){
        assertEquals(89, classUnderTest.foldRightIndexedList())
    }

    @Test
    fun whenReduceList_thenReturnsValue(){
        assertEquals(-25, classUnderTest.reduceList())
    }

    @Test
    fun whenReduceRightList_thenReturnsValue(){
        assertEquals(-11, classUnderTest.reduceRightList())
    }

    @Test
    fun whenReduceIndexedList_thenReturnsValue(){
        assertEquals(-10, classUnderTest.reduceIndexedList())
    }

    @Test
    fun whenReduceRightIndexedList_thenReturnsValue(){
        assertEquals(5, classUnderTest.reduceRightIndexedList())
    }

    @Test
    fun whenRunningFold_shouldReturnAllTheIntermediateResults() {
        val numbers = listOf(1, 2, 3, 4, 5)
        assertEquals(listOf(0, 1, 3, 6, 10, 15), numbers.runningFold(0) {total, it -> total + it})
    }

    @Test
    fun whenRunningReduce_shouldReturnAllTheIntermediateResults() {
        val numbers = listOf(1, 2, 3, 4, 5)
        assertEquals(listOf(1, 3, 6, 10, 15), numbers.runningReduce { total, it -> total + it })
    }
}