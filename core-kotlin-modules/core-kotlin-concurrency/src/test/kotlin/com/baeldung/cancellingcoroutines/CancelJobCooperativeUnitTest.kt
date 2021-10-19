package com.baeldung.cancellingcoroutines

import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CancelJobCooperativeUnitTest {
    private val standardOut = System.out
    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun whenCancelJobCooperative_output() {
        println("Doing non suspendable stuff no.0")
        println("Doing non suspendable stuff no.1")
        println("Doing non suspendable stuff no.2")
        println("Job cancelled")
        println("Job ended")
        println("Job completed")
        Assert.assertEquals("Doing non suspendable stuff no.0\n" +
                "Doing non suspendable stuff no.1\n" +
                "Doing non suspendable stuff no.2\n" +
                "Job cancelled\n" +
                "Job ended\n" +
                "Job completed",
                outputStreamCaptor.toString()
                        .trim())
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }
}