package com.baeldung.break_continue.functional_loops

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class BreakContinueFunctionalLoops {
    @Test
    fun `given functional loop when labeled return then simulate continue`() {
        val list = listOf(3, 4, 3, 4, 3)
        var sum = 0
        list.forEach loop@{ number ->
            if (number % 2 == 0) { // skip all even numbers
                return@loop
            }
            sum += number
        }
        assertTrue { sum == 9 }
    }

    @Test
    fun `given functional loop when nested labeled return then simulate break`() {
        val list = listOf(3, 4, 3, 4, 3)
        var sum = 0
        run outer@{
            list.forEach inner@{ number ->
                if (number % 2 == 0) { // 'break' at the first even number
                    return@outer
                }
                sum += number
            }
        }
        assertTrue { sum == 3 }
    }

    @Test
    fun `given functional loop when filter used then simulate continue`() {
        val list = listOf(3, 4, 3, 4, 3)
        var sum = 0
        list.filter { it % 2 != 0 }
            .forEach { number ->
                sum += number
            }
        assertTrue { sum == 9 }
    }

    @Test
    fun `given functional loop when takeWhile used then simulate break`() {
        val list = listOf(3, 4, 3, 4, 3)
        var sum = 0
        list.takeWhile { it % 2 != 0 }
            .forEach { number ->
                sum += number
            }
        assertTrue { sum == 3 }
    }
}
