package com.baeldung.multiCatch

import com.baeldung.multiCatch.SaveKeyResult.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass
import kotlin.test.assertEquals

class KeyTooLongException(message: String = "Key-length must be six.") : Exception(message)
class KeyTooShortException(message: String = "Key-length must be six.") : Exception(message)
class InvalidKeyException(message: String = "Key should only contain digits.") : Exception(message)
class KeyAlreadyExistsException(message: String = "Key exists already.") : Exception(message)

object KeyService {
    private val keyStore = mutableSetOf<String>()
    fun clearStore() = keyStore.clear()
    fun saveSixDigitsKey(digits: String) {
        when {
            digits.length < 6 -> throw KeyTooShortException()
            digits.length > 6 -> throw KeyTooLongException()
            digits.matches(Regex("""\d{6}""")).not() -> throw InvalidKeyException()
            digits in keyStore -> throw KeyAlreadyExistsException()
            else -> keyStore += digits
        }
    }
}

enum class SaveKeyResult {
    SUCCESS, FAILED, SKIPPED_EXISTED_KEY
}

fun save1(theKey: String): SaveKeyResult {
    return try {
        KeyService.saveSixDigitsKey(theKey)
        SUCCESS
    } catch (ex: KeyTooShortException) {
        FAILED
    } catch (ex: KeyTooLongException) {
        FAILED
    } catch (ex: InvalidKeyException) {
        FAILED
    } catch (ex: KeyAlreadyExistsException) {
        SKIPPED_EXISTED_KEY
    }
}

fun save2(theKey: String): SaveKeyResult {
    return try {
        KeyService.saveSixDigitsKey(theKey)
        SUCCESS
    } catch (ex: Exception) {
        when (ex) {
            is KeyTooLongException,
            is KeyTooShortException,
            is InvalidKeyException -> FAILED
            is KeyAlreadyExistsException -> SKIPPED_EXISTED_KEY
            else -> throw ex
        }
    }
}

inline fun <R> (() -> R).multiCatch(vararg exceptions: KClass<out Throwable>, thenDo: () -> R): R {
    return try {
        this()
    } catch (ex: Exception) {
        if (ex::class in exceptions) thenDo() else throw ex
    }
}

fun save3(theKey: String): SaveKeyResult {
    return try {
        {
            KeyService.saveSixDigitsKey(theKey)
            SUCCESS
        }.multiCatch(
            KeyTooShortException::class,
            KeyTooLongException::class,
            InvalidKeyException::class
        ) { FAILED }
    } catch (ex: KeyAlreadyExistsException) {
        SKIPPED_EXISTED_KEY
    }
}

inline fun <R, T : R> Result<T>.raise(
    vararg exceptions: KClass<out Throwable>,
    transform: (exception: Throwable) -> T
) = recoverCatching { ex ->
    if (ex::class in exceptions) {
        transform(ex)
    } else throw ex
}

fun save4(theKey: String): SaveKeyResult {
    return runCatching {
        KeyService.saveSixDigitsKey(theKey)
        SUCCESS
    }.raise(
        KeyTooShortException::class,
        KeyTooLongException::class,
        InvalidKeyException::class
    ) {
        FAILED
    }.raise(KeyAlreadyExistsException::class) {
        SKIPPED_EXISTED_KEY
    }.getOrThrow()
}

//Test
class MultiCatchUnitTest {
    @BeforeEach
    fun cleanup() {
        KeyService.clearStore()
    }

    @Test
    fun `when call the save1 function, should get expected result`() {
        assertEquals(FAILED, save1("42"))
        assertEquals(FAILED, save1("1234567"))
        assertEquals(FAILED, save1("kotlin"))
        assertEquals(SUCCESS, save1("123456"))
        assertEquals(SKIPPED_EXISTED_KEY, save1("123456"))
    }

    @Test
    fun `when call the save2 function, should get expected result`() {
        assertEquals(FAILED, save2("42"))
        assertEquals(FAILED, save2("1234567"))
        assertEquals(FAILED, save2("kotlin"))
        assertEquals(SUCCESS, save2("123456"))
        assertEquals(SKIPPED_EXISTED_KEY, save2("123456"))
    }

    @Test
    fun `when call the save3 function, should get expected result`() {
        assertEquals(FAILED, save3("42"))
        assertEquals(FAILED, save3("1234567"))
        assertEquals(FAILED, save3("kotlin"))
        assertEquals(SUCCESS, save3("123456"))
        assertEquals(SKIPPED_EXISTED_KEY, save3("123456"))
    }

    @Test
    fun `when call the save4 function, should get expected result`() {
        assertEquals(FAILED, save4("1234567"))
        assertEquals(FAILED, save4("42"))
        assertEquals(FAILED, save4("kotlin"))
        assertEquals(SUCCESS, save4("123456"))
        assertEquals(SKIPPED_EXISTED_KEY, save4("123456"))
    }
}