package com.baeldung.kotlin.arrow

import arrow.core.Either
import com.baeldung.kotlin.arrow.FunctionalErrorHandlingWithEither.ComputeProblem.NotANumber
import com.baeldung.kotlin.arrow.FunctionalErrorHandlingWithEither.ComputeProblem.OddNumber
import kotlin.test.assertTrue
import kotlin.test.fail
import org.junit.jupiter.api.Test

class FunctionalErrorHandlingWithEitherUnitTest {

    private val operator = FunctionalErrorHandlingWithEither()

    @Test
    fun givenInvalidInput_whenComputeInvoked_NotANumberIsPresent(){
        val computeWithEither = operator.computeWithEither("bar")

        assertTrue(computeWithEither.isLeft())
        when(computeWithEither){
            is Either.Left -> when(computeWithEither.a){
                NotANumber -> "Ok."
                else -> fail()
            }
            else -> fail()
        }
    }

    @Test
    fun givenOddNumberInput_whenComputeInvoked_OddNumberIsPresent(){
        val computeWithEither = operator.computeWithEither("121")

        assertTrue(computeWithEither.isLeft())
        when(computeWithEither){
            is Either.Left -> when(computeWithEither.a){
                OddNumber -> "Ok."
                else -> fail()
            }
            else -> fail()
        }
    }

    @Test
    fun givenEvenNumberWithoutSquare_whenComputeInvoked_OddNumberIsPresent(){
        val computeWithEither = operator.computeWithEither("100")

        assertTrue(computeWithEither.isRight())
        when(computeWithEither){
            is Either.Right -> when(computeWithEither.b){
                false -> "Ok."
                else -> fail()
            }
            else -> fail()
        }
    }

    @Test
    fun givenEvenNumberWithSquare_whenComputeInvoked_OddNumberIsPresent(){
        val computeWithEither = operator.computeWithEither("98")

        assertTrue(computeWithEither.isRight())
        when(computeWithEither){
            is Either.Right -> when(computeWithEither.b){
                true -> "Ok."
                else -> fail()
            }
            else -> fail()
        }
    }
}