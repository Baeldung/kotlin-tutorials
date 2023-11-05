package com.baeldung.kotlin.arrow

import arrow.core.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class FunctionalDataTypesUnitTest {

    @Test
    fun whenOptionCreated_thanValueIsPresent(){
        val factory = Some(42)
        val constructor = Option(42)
        val emptyOptional: Option<Int> = none()
        val fromNullable = Option.fromNullable(null)

        assertEquals(42, factory.getOrElse { -1 })
        assertEquals(factory, constructor)
        assertEquals(emptyOptional, fromNullable)
    }

    @Test
    fun whenOptionCreated_thanConstructorDifferFromFactory(){
        val constructor : Option<String?> = Option(null)
        val fromNullable : Option<String?> = Option.fromNullable(null)

        try{
            constructor.map { s -> s!!.length }
            fail()
        } catch (e : NullPointerException){
            fromNullable.map { s->s!!.length }
        }
        assertNotEquals(constructor, fromNullable)
    }

    fun wrapper(x : Int?) : Option<Int> = if (x == null) Some(-1) else Some(x.toInt())

    @Test
    fun whenOptionFromNullableCreated_thanItBreaksLeftIdentity(){
        val optionFromNull = Option.fromNullable(null)

        assertNotEquals(optionFromNull.flatMap(::wrapper), wrapper(null))
    }

    @Test
    fun whenEitherCreated_thanOneValueIsPresent(){
        val rightOnly : Either<String,Int> = Either.Right(42)
        val leftOnly : Either<String,Int> = Either.Left("foo")

        assertTrue(rightOnly.isRight())
        assertTrue(leftOnly.isLeft())
        assertEquals(42, rightOnly.getOrElse { -1 })
        assertEquals(-1, leftOnly.getOrElse { -1 })

        assertEquals(0, rightOnly.map { it % 2 }.getOrElse { -1 })
        assertEquals(-1, leftOnly.map { it % 2 }.getOrElse { -1 })
        assertTrue(rightOnly.flatMap { Either.Right(it % 2) }.isRight())
        assertTrue(leftOnly.flatMap { Either.Right(it % 2) }.isLeft())
    }

    @Test
    fun whenEvalNowUsed_thenMapEvaluatedLazily(){
        val now = Eval.now(1)
        assertEquals(1, now.value())

        var counter : Int = 0
        val map = now.map { x -> counter++; x+1 }
        assertEquals(0, counter)

        val value = map.value()
        assertEquals(2, value)
        assertEquals(1, counter)
    }

    @Test
    fun whenEvalLaterUsed_theResultIsMemoized(){
        var counter : Int = 0
        val later = Eval.later { counter++; counter }
        assertEquals(0, counter)

        val firstValue = later.value()
        assertEquals(1, firstValue)
        assertEquals(1, counter)

        val secondValue = later.value()
        assertEquals(1, secondValue)
        assertEquals(1, counter)
    }

    @Test
    fun whenEvalAlwaysUsed_theResultIsNotMemoized(){
        var counter : Int = 0
        val later = Eval.always { counter++; counter }
        assertEquals(0, counter)

        val firstValue = later.value()
        assertEquals(1, firstValue)
        assertEquals(1, counter)

        val secondValue = later.value()
        assertEquals(2, secondValue)
        assertEquals(2, counter)
    }

}