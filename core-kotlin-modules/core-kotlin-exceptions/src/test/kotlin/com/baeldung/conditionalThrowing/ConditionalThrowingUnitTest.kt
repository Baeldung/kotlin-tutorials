package com.baeldung.conditionalThrowing

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals


class MyException(msg: String) : Exception(msg)

inline fun throwIf(throwCondition: Boolean, exProvider: () -> Exception) {
    if (throwCondition) throw exProvider()
}

inline fun <T : Any?> T.mustHave(
    otherwiseThrow: (T) -> Exception = { IllegalStateException("mustHave check failed: $it") },
    require: (T) -> Boolean
): T {
    if (!require(this)) throw otherwiseThrow(this)
    return this
}

data class Player(val id: Int, val name: String, val score: Int)
class InvalidPlayerException(message: String) : RuntimeException(message)

class ConditionalThrowingUnitTest {

    @Test
    fun `when using require() then get expected results`() {
        val str = "a b c"
        assertThrows<IllegalArgumentException> {
            require(str.length > 10) { "The string is too short." }
        }

        val upperStr = str.also {
            require(it.split(" ").size == 3) { "Format not supported" }
        }.uppercase()
        assertEquals("A B C", upperStr)


        var nullableValue: String? = null
        assertThrows<IllegalArgumentException> {
            requireNotNull(nullableValue) { "Null is not allowed" }
        }

        nullableValue = "a b c"
        val uppercaseValue = requireNotNull(nullableValue).uppercase()
        assertEquals("A B C", uppercaseValue)
    }

    @Test
    fun `when using check() then get expected results`() {
        val str = "a b c"
        assertThrows<IllegalStateException> {
            check(str.length > 10) { "The string is too short." }
        }

        var nullableValue: String? = null
        assertThrows<IllegalStateException> {
            checkNotNull(nullableValue) { "Null is not allowed" }
        }

        nullableValue = "a b c"
        val uppercaseValue = checkNotNull(nullableValue).uppercase()
        assertEquals("A B C", uppercaseValue)
    }


    @Test
    fun `when using takeIf() then get expected results`() {
        val str = "a b c"
        assertThrows<MyException> {
            str.takeIf { it.length > 10 } ?: throw MyException("The string is too short.")
        }

        val nullIsValid: String? = null
        assertThrows<MyException> {
            nullIsValid.takeIf { true } ?: throw MyException("The string is too short.")
        }
    }

    @Test
    fun `when using throwIf() then get expected results`() {
        val str = "a b c"
        assertThrows<MyException> {
            throwIf(str.length <= 10) { MyException("The string is too short.") }
        }

        val uppercaseValue = str.also {
            throwIf(it.split(" ").size != 3) { MyException("Format not supported") }
        }.uppercase()
        assertEquals("A B C", uppercaseValue)
    }


    @Test
    fun `when using mustHave() then get expected results`() {
        val kai = Player(1, "Kai", -5)
        assertThrows<IllegalStateException> { kai.mustHave { it.score >= 0 } }
            .also { ex -> assertEquals("mustHave check failed: Player(id=1, name=Kai, score=-5)", ex.message) }

        assertThrows<InvalidPlayerException> {
            kai.mustHave(
                require = { it.score >= 0 },
                otherwiseThrow = { InvalidPlayerException("Player [id=${it.id}, name=${it.name}] is invalid") }
            )
        }.also { ex -> assertEquals("Player [id=1, name=Kai] is invalid", ex.message) }

        val liam = Player(2, "Liam", 42)
        val upperDescription = liam.mustHave(
            require = { it.score >= 0 },
            otherwiseThrow = { InvalidPlayerException("Player [id=${it.id}, name=${it.name}] is invalid") }
        ).let { "${it.name} : ${it.score}".uppercase() }
        assertEquals("LIAM : 42", upperDescription)
    }
}