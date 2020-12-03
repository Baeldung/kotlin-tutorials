package com.baeldung.static

import org.junit.jupiter.api.Test

class LoggingUtilsUnitTest {
    @Test
    fun givenAPackageMethod_whenCalled_thenNoErrorIsThrown() {
        debug("test message")
    }
}