package com.baeldung.sealedclasses.vs.enums

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OsSealedUnitTest {
    @Test
    fun detect_os() {
        detectOsByUsingWhen(OsSealed.Linux)
        detectOsByUsingWhen(OsSealed.Windows)
        detectOsByUsingWhen(OsSealed.Mac)
        detectOsByUsingWhen(OsSealed.Unknown)
    }

    private fun detectOsByUsingWhen(osSealed: OsSealed) {
        assertEquals("Called from parent sealed class", osSealed.getTextParent())
        when (osSealed) {
            is OsSealed.Linux -> assertEquals("Linux by Open-Source - value=1", osSealed.getText(1))
            is OsSealed.Mac -> assertEquals("Mac by Apple - released at 2001", osSealed.doSomething())
            is OsSealed.Windows -> assertEquals(5, osSealed.getNumber("Text!"))
            else -> assertTrue(osSealed is OsSealed.Unknown)
        }
    }

    private fun detectOsByUsingWhenPrint(osSealed: OsSealed) {
        when (osSealed) {
            OsSealed.Linux -> println("${osSealed.company} - Linux Operating System")
            OsSealed.Mac -> println("${osSealed.company} - Mac Operating System")
            OsSealed.Windows -> println("${osSealed.company} - Windows Operating System")
            else -> println(osSealed.company)
        }
    }
}
