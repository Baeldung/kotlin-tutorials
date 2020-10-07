package com.baeldung.nonnull

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception
import java.lang.IllegalArgumentException

class NonNullUnitTest {

    @Test
    fun `Given a non-null field, When setting it to null, Then Kotlin throws an exception`() {
        val constructor = User::class.java.constructors[0]
        val createInstance = { constructor.newInstance(42L, null) }

        val exception = assertThrows<Exception> { createInstance() }
        assertThat(exception.cause).isInstanceOf(NullPointerException::class.java)
        assertThat(exception.cause?.message).startsWith("Parameter specified as non-null is null")
    }
}