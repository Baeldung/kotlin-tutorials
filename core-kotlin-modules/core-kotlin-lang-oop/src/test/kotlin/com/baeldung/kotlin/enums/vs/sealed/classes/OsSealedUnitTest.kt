package com.baeldung.kotlin.enums.vs.sealed.classes

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OsSealedUnitTest {
    @Test
    fun detect_os() {
        detectOsByUsingWhen(OsSealed.Linux)
        detectOsByUsingWhen(OsSealed.Windows)
        detectOsByUsingWhen(OsSealed.Mac)
        detectOsByUsingWhen(OsSealed.Unknown)
    }

    private fun detectOsByUsingWhen(osSealed: OsSealed) {
        when (osSealed) {
            is OsSealed.Linux -> assertEquals("Linux by Open-Source - value=1", osSealed.getText(1))
            is OsSealed.Mac -> assertEquals("Mac by Apple - released at 2001", osSealed.doSomething())
            is OsSealed.Windows -> assertEquals(5, osSealed.getNumber("Text!"))
            else -> assert(osSealed is OsSealed.Unknown)
        }
    }
}