package com.baeldung.addmultiplymatrix

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class AddMultiplyMatrix {
    @Test
    fun `addition of matrices`() {
        val matrix1 = arrayOf(
            arrayOf(1, 2),
            arrayOf(3, 4)
        )

        val matrix2 = arrayOf(
            arrayOf(5, 6),
            arrayOf(7, 8)
        )

        val expected = arrayOf(
            arrayOf(6, 8),
            arrayOf(10, 12)
        )

        assertArrayEquals(expected, addMatrices(matrix1, matrix2))
    }

    @Test
    fun `multiplication of Matrices`() {
        val matrix1 = arrayOf(
            arrayOf(1, 2),
            arrayOf(3, 4)
        )

        val matrix2 = arrayOf(
            arrayOf(5, 6),
            arrayOf(7, 8)
        )

        val expected = arrayOf(
            arrayOf(19, 22),
            arrayOf(43, 50)
        )

        assertArrayEquals(expected, multiplyMatrices(matrix1, matrix2))
    }
}
fun addMatrices(matrix1: Array<Array<Int>>, matrix2: Array<Array<Int>>): Array<Array<Int>> {
    val row = matrix1.size
    val col = matrix1[0].size
    val sum = Array(row) { Array(col) { 0 } }

    for (i in 0 until row) {
        for (j in 0 until col) {
            sum[i][j] = matrix1[i][j] + matrix2[i][j]
        }
    }

    return sum
}

fun multiplyMatrices(matrix1: Array<Array<Int>>, matrix2: Array<Array<Int>>): Array<Array<Int>> {
    val row1 = matrix1.size
    val col1 = matrix1[0].size
    val col2 = matrix2[0].size
    val product = Array(row1) { Array(col2) { 0 } }

    for (i in 0 until row1) {
        for (j in 0 until col2) {
            for (k in 0 until col1) {
                product[i][j] += matrix1[i][k] * matrix2[k][j]
            }
        }
    }

    return product
}