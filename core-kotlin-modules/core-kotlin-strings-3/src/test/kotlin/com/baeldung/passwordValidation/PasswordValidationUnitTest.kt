package com.baeldung.passwordValidation

import org.junit.jupiter.api.Test
import kotlin.reflect.KFunction1
import kotlin.test.assertEquals
import kotlin.test.assertTrue

const val ERR_LEN = "Password must have at least eight characters!"
const val ERR_WHITESPACE = "Password must not contain whitespace!"
const val ERR_DIGIT = "Password must contain at least one digit!"
const val ERR_UPPER = "Password must have at least one uppercase letter!"
const val ERR_SPECIAL = "Password must have at least one special character, such as: _%-=+#@"

fun validatePassword(pwd: String) = runCatching {
    require(pwd.length >= 8) { ERR_LEN }
    require(pwd.none { it.isWhitespace() }) { ERR_WHITESPACE }
    require(pwd.any { it.isDigit() }) { ERR_DIGIT }
    require(pwd.any { it.isUpperCase() }) { ERR_UPPER }
    require(pwd.any { !it.isLetterOrDigit() }) { ERR_SPECIAL }
}

@JvmInline
value class Password(val pwd: String) : CharSequence by pwd {
    fun longerThan8Rule() = require(pwd.length >= 8) { ERR_LEN }
    fun withoutWhitespaceRule() = require(pwd.none { it.isWhitespace() }) { ERR_WHITESPACE }
    fun hasDigitRule() = require(pwd.any { it.isDigit() }) { ERR_DIGIT }
    fun hasUppercaseRule() = require(pwd.any { it.isUpperCase() }) { ERR_UPPER }
    fun hasSpecialCharRule() = require(pwd.any { !it.isLetterOrDigit() }) { ERR_SPECIAL }

    infix fun checkWith(rules: List<KFunction1<Password, Unit>>) = runCatching { rules.forEach { it(this) } }
}

class PasswordValidationUnitTest {
    val tooShort = "a1A-"
    val noDigit = "_+Addabc"
    val withSpace = "abcd A#1#"
    val noUpper = "1234abc#"
    val noSpecial = "1abcdABCD"
    val okPwd = "1234abc#ABC"

    @Test
    fun `when using if else should validate the password`() {
        validatePassword(tooShort).apply {
            assertTrue { isFailure }
            assertEquals(ERR_LEN, exceptionOrNull()?.message)
        }

        validatePassword(noDigit).apply {
            assertTrue { isFailure }
            assertEquals(ERR_DIGIT, exceptionOrNull()?.message)
        }

        validatePassword(withSpace).apply {
            assertTrue { isFailure }
            assertEquals(ERR_WHITESPACE, exceptionOrNull()?.message)
        }

        validatePassword(noUpper).apply {
            assertTrue { isFailure }
            assertEquals(ERR_UPPER, exceptionOrNull()?.message)
        }

        validatePassword(noSpecial).apply {
            assertTrue { isFailure }
            assertEquals(ERR_SPECIAL, exceptionOrNull()?.message)
        }

        validatePassword(okPwd).apply {
            assertTrue { isSuccess }
        }
    }

    @Test
    fun `when using string extensions should validate the password`() {
        fun String.longerThan8() = require(length >= 8) { ERR_LEN }
        fun String.withoutWhitespace() = require(none { it.isWhitespace() }) { ERR_WHITESPACE }
        fun String.hasDigit() = require(any { it.isDigit() }) { ERR_DIGIT }
        fun String.hasUppercase() = require(any { it.isUpperCase() }) { ERR_UPPER }
        fun String.hasSpecialChar() = require(any { !it.isLetterOrDigit() }) { ERR_SPECIAL }

        val checks = listOf(
            String::longerThan8,
            String::withoutWhitespace,
            String::hasDigit,
            String::hasUppercase,
            String::hasSpecialChar,
        )

        runCatching { checks.forEach { it(noDigit) } }.apply {
            assertTrue { isFailure }
            assertEquals(ERR_DIGIT, exceptionOrNull()?.message)
        }

        runCatching { checks.forEach { it(withSpace) } }.apply {
            assertTrue { isFailure }
            assertEquals(ERR_WHITESPACE, exceptionOrNull()?.message)
        }

        runCatching { checks.forEach { it(noUpper) } }.apply {
            assertTrue { isFailure }
            assertEquals(ERR_UPPER, exceptionOrNull()?.message)
        }

        runCatching { checks.forEach { it(noSpecial) } }.apply {
            assertTrue { isFailure }
            assertEquals(ERR_SPECIAL, exceptionOrNull()?.message)
        }

        runCatching { checks.forEach { it(okPwd) } }.apply {
            assertTrue { isSuccess }
        }
    }

    @Test
    fun `when using value class should validate the password`() {
        val rules = listOf(
            Password::hasDigitRule,
            Password::longerThan8Rule,
            Password::withoutWhitespaceRule,
            Password::hasSpecialCharRule,
            Password::hasUppercaseRule
        )
        (Password(tooShort) checkWith rules).apply {
            assertTrue { isFailure }
            assertEquals(ERR_LEN, exceptionOrNull()?.message)
        }

        (Password(noDigit) checkWith rules).apply {
            assertTrue { isFailure }
            assertEquals(ERR_DIGIT, exceptionOrNull()?.message)
        }

        (Password(withSpace) checkWith rules).apply {
            assertTrue { isFailure }
            assertEquals(ERR_WHITESPACE, exceptionOrNull()?.message)
        }

        (Password(noUpper) checkWith rules).apply {
            assertTrue { isFailure }
            assertEquals(ERR_UPPER, exceptionOrNull()?.message)
        }

        (Password(noSpecial) checkWith rules).apply {
            assertTrue { isFailure }
            assertEquals(ERR_SPECIAL, exceptionOrNull()?.message)
        }

        (Password(okPwd) checkWith rules).apply {
            assertTrue { isSuccess }
        }
    }


    @Test
    fun `when using value class adding new rules should validate the password`() {
        fun Password.withoutAdminRule() = require("admin" !in pwd.lowercase()) { "Admin password cannot contain 'admin'" }
        fun Password.longerThan10Rule() = require(pwd.length >= 10) { "Admin password must be longer than 10!" }

        val adminPwdOK = "1234abCD%%@"
        val withAdmin = "1234adMIN%%@"
        val shortPwd = "1234adX%@" //len=9
        val adminRules = listOf(
            Password::hasDigitRule,
            Password::withoutWhitespaceRule,
            Password::hasSpecialCharRule,
            Password::hasUppercaseRule,

            Password::withoutAdminRule,
            Password::longerThan10Rule
        )

        (Password(shortPwd) checkWith adminRules).apply {
            assertTrue { isFailure }
            assertEquals("Admin password must be longer than 10!", exceptionOrNull()?.message)
        }

        (Password(withAdmin) checkWith adminRules).apply {
            assertTrue { isFailure }
            assertEquals("Admin password cannot contain 'admin'", exceptionOrNull()?.message)
        }

        (Password(adminPwdOK) checkWith adminRules).apply {
            assertTrue { isSuccess }
        }
    }
}