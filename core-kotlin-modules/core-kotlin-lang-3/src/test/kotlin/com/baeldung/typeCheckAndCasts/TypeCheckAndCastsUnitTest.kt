package com.baeldung.typeCheckAndCasts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertFailsWith

fun isString(obj: Any): Boolean = obj is String

fun isNotString(obj: Any): Boolean = obj !is String

fun unsafeCastToString(obj: Any): String = obj as String

fun safeCastToString(obj: Any): String? = obj as? String

fun doubleTheValue(obj: Any): Any =
    when (obj) {
        is String -> obj.repeat(2)
        is Long -> obj * 2
        is List<*> -> obj + obj
        else -> "Unsupported Type Found"
    }

inline fun <reified T> Any.isType() = this is T
inline fun <reified T> Any.isTypedList() = this is List<*> && all { it is T }

class TypeCheckAndSmartCastsUnitTest {
    @Test
    fun `given type check functions when pass an object should get expected result`() {
        val aString: Any = "I am a String"
        val aLong: Any = 42L

        assertThat(isString(aString)).isTrue
        assertThat(isString(aLong)).isFalse

        assertThat(isNotString(aString)).isFalse
        assertThat(isNotString(aLong)).isTrue
    }

    @Test
    fun `given an object when check type without generic type parameter should get expected exception`() {
        val aStringList: Any = listOf("string1", "string2", "string3")
//         assertThat(aStringList is List<String>).isTrue() // doesn't compile!!
        assertThat(aStringList is List<*>).isTrue
    }

    @Test
    fun `given generic type check functions when check type with isType() should get expected incorrect result`() {
        val aStringList: Any = listOf("string1", "string2", "string3")
        assertThat(aStringList.isType<List<String>>()).isTrue

        val anIntList: Any = listOf(1, 2, 3)
        assertThat(anIntList.isType<List<String>>()).isTrue //expect: false
    }

    @Test
    fun `given generic type check functions when check type with type parameters should get expected result`() {
        val aStringList: Any = listOf("string1", "string2", "string3")
        assertThat(aStringList.isTypedList<String>()).isTrue


        val anIntList: Any = listOf(1, 2, 3)
        assertThat(anIntList.isTypedList<String>()).isFalse
        assertThat(anIntList.isTypedList<Int>()).isTrue
    }

    @Test
    fun `given unsafe cast function when pass an object should get expected result`() {
        val aString: Any = "I am a String"
        val aLong: Any = 42L

        assertThat(unsafeCastToString(aString)).isEqualTo(aString)
        assertFailsWith<java.lang.ClassCastException> {
            unsafeCastToString(aLong)
        }
    }

    @Test
    fun `given safe cast function when pass an object should get expected result`() {
        val aString: Any = "I am a String"
        val aLong: Any = 42L

        assertThat(safeCastToString(aString)).isEqualTo(aString)
        assertThat(safeCastToString(aLong)).isNull()
    }

    @Test
    fun `given smart casts when pass an object should get expected result`() {
        val aString: Any = "I am a String"
        val aLong: Any = 42L
        val aList: Any = listOf(1, 2, 3)
        val aDate: Any = Instant.now()

        assertThat(doubleTheValue(aString)).isEqualTo("$aString$aString")
        assertThat(doubleTheValue(aLong)).isEqualTo(84L)
        assertThat(doubleTheValue(aList)).isEqualTo(listOf(1, 2, 3, 1, 2, 3))
        assertThat(doubleTheValue(aDate)).isEqualTo("Unsupported Type Found")
    }


}