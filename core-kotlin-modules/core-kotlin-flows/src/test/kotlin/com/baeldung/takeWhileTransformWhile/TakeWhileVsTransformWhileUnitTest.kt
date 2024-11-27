package com.baeldung.concatenateTwoFlows

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TakeWhileVsTransformWhileUnitTest {

    @Test
    fun `takeWhile less than 3 returns 1 and 2`() {
        runBlocking {
            val numbersFlow = flowOf(1, 2, 3, 4, 5)

            val taken = numbersFlow.takeWhile { it < 3 }.toList()

            Assertions.assertEquals(listOf(1, 2), taken)
        }
    }

    @Test
    fun `transformWhile less than 3 can return 1, 2 and 3`() {
        runBlocking {
            val numbersFlow = flowOf(1, 2, 3, 4, 5)

            val transformed = numbersFlow.transformWhile {
                emit(it)
                emit(it)
                it < 3
            }.toList()

            Assertions.assertEquals(listOf(1, 1, 2, 2, 3, 3), transformed)
        }
    }

    @Test
    fun `transformWhile can transform elements`() {
        runBlocking {
            val numbersFlow = flowOf(1, 2, 3, 4, 5)

            val transformed = numbersFlow.transformWhile {
                emit("" + it)
                emit("" + it * 2)
                it < 3
            }.toList()

            Assertions.assertEquals(listOf("1", "2", "2", "4", "3", "6"), transformed)
        }
    }
}
