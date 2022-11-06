package com.baeldung.multidimensionalArray

import com.baeldung.array.printMultidimArray
import com.baeldung.array.printMultidimArrayNullable
import org.assertj.core.api.Assertions
import org.junit.Test

class MultidimensionalArrayTest {

    data class Pixel(val red: Int, val green: Int, val blue: Int)

    @Test
    fun `create unidimensional array with Pixel class`() {
        val pixelArray: Array<Pixel> = Array(10) {
            Pixel(
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            )
        }
        for (i in 0..9) {
            Assertions.assertThat((pixelArray[i].red in 0..255))
            Assertions.assertThat((pixelArray[i].blue in 0..255))
            Assertions.assertThat((pixelArray[i].green in 0..255))
        }
    }

    @Test
    fun `create bidimensional array with Pixel class`() {
        val matrix: Array<Array<Pixel>> = Array(10) {
            Array(10) {
                Pixel(
                    (0..255).random(),
                    (0..255).random(),
                    (0..255).random()
                )
            }
        }
        for (i in 0..9) {
            for (j in 0..9) {
                Assertions.assertThat((matrix[i][j].red in 0..255))
                Assertions.assertThat((matrix[i][j].blue in 0..255))
                Assertions.assertThat((matrix[i][j].green in 0..255))
            }
            println()
        }
    }

    @Test
    fun `create thredimensional array with Pixel class`() {
        val video: Array<Array<Array<Pixel>>> = Array(10) {
            Array(10) {
                Array(10) {
                    Pixel(
                        (0..255).random(),
                        (0..255).random(),
                        (0..255).random()
                    )
                }
            }
        }

        for (i in 0..9) {
            for (j in 0..9) {
                for (k in 0..9) {
                    Assertions.assertThat((video[i][j][k].red in 0..255))
                    Assertions.assertThat((video[i][j][k].blue in 0..255))
                    Assertions.assertThat((video[i][j][k].green in 0..255))
                }
            }
        }
    }

    @Test
    fun `create multidimensional array with library function`() {
        val array = Array(10) { Array(20) { 5 } }
        printMultidimArray(array)
        Assertions.assertThat(array[5][10] == 5)

        val array2 = Array(10) { IntArray(20) }
        printMultidimArray(array2)
        Assertions.assertThat(array2[5][10] == 0)

        val first: Array<IntArray> = arrayOf(
            intArrayOf(2, 4, 6),
            intArrayOf(1, 3, 5)
        )
        printMultidimArray(first)
    }

    @Test
    fun `create nullable multidimensional array`() {
        val arrayNulls = arrayOfNulls<Array<Int>>(3)
        arrayNulls[0] = Array(2) { 0 }
        arrayNulls[1] = Array(4) { 1 }
        printMultidimArrayNullable(arrayNulls)
        Assertions.assertThat(arrayNulls[2] == null)
        Assertions.assertThat(arrayNulls[0]!!.size == 2)
        Assertions.assertThat(arrayNulls[1]!!.size == 4)
    }
}