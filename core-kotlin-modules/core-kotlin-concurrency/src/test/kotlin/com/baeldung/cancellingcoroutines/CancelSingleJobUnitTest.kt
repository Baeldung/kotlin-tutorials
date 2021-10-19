package com.baeldung.cancellingcoroutines

import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CancelSingleJobUnitTest {
    private val standardOut = System.out
    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun whenCancelSingleJob_output() {
        println("Fetching album no.0")
        println("Fetching album no.1")
        println("Fetching album no.2")
        println("Canceling fetching albums job")
        println("Fetching albums job completed")
        Assert.assertEquals("Fetching album no.0\n" +
                "Fetching album no.1\n" +
                "Fetching album no.2\n" +
                "Canceling fetching albums job\n" +
                "Fetching albums job completed",
                outputStreamCaptor.toString()
                        .trim())
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }
}