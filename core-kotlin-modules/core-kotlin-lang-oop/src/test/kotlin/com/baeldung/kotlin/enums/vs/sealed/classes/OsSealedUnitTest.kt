package com.baeldung.kotlin.enums.vs.sealed.classes

import org.junit.jupiter.api.Test

class OsSealedUnitTest {
    @Test
    fun detect_os() {
        detectOsByUsingWhen(OsSealed.Linux)
        detectOsByUsingWhen(OsSealed.Windows)
        detectOsByUsingWhen(OsSealed.Mac)
        detectOsByUsingWhen(OsSealed.Unknown)
    }

    private fun detectOsByUsingWhen(osSealed: OsSealed) {
        osSealed.printParent()
        when (osSealed) {
            is OsSealed.Linux -> println(osSealed.getText(1))
            is OsSealed.Mac -> osSealed.doSomething()
            is OsSealed.Windows -> println("Windows - ${osSealed.getNumber("Text!")}")
            else -> println("Unknown osSealed: $osSealed ")
        }
    }
}