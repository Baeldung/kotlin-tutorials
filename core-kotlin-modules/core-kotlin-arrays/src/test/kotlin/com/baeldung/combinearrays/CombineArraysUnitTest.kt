package com.baeldung.combinearrays

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CombineArraysUnitTest {

    val arr1 = arrayOf(3, 10, 2)
    val arr2 = arrayOf(6, 11, 89, 0)

    val expectedMergedArray = arrayOf(3, 10, 2, 6, 11, 89, 0)

    lateinit var mergeArrays: CombineArrays

    @BeforeEach
    fun setUp() {
        mergeArrays = CombineArrays()
    }

    @Test
    fun `Given two arrays, when combinePlus is called, then arrays are merged`() {
        assertEqualArrays(expectedMergedArray, mergeArrays.combinePlus(arr1, arr2))
    }

    @Test
    fun `Given two arrays, when combinePlusOperator is called, then arrays are merged`() {
        assertEqualArrays(expectedMergedArray, mergeArrays.combinePlusOperator(arr1, arr2))
    }

    @Test
    fun `Given two arrays, when combineSpread is called, then arrays are merged`() {
        assertEqualArrays(expectedMergedArray, mergeArrays.combineSpread(arr1, arr2))
    }

    @Test
    fun `Given two arrays, when combineCustom is called, then arrays are merged`() {
        assertEqualArrays(expectedMergedArray, mergeArrays.combineCustom(arr1, arr2))
    }

    private fun assertEqualArrays(arr1: Array<Int>, arr2: Array<Int>) {
        assertEquals(arr1.contentToString(), arr2.contentToString())
    }
}


