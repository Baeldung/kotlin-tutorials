package com.baeldung.cancellingcoroutines

import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CancelMultipleJobsUnitTest {
    private val standardOut = System.out
    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun whenCancelMultipleJobs_output() {
        println("Fetching album no.0")
        println("Fetching song no.0")
        println("Fetching song no.1")
        println("Fetching song no.2")
        println("Fetching albums cancelled")
        println("Fetching albums ended")
        println("Fetching albums job completed")
        Assert.assertEquals("Fetching album no.0\n" +
                "Fetching song no.0\n" +
                "Fetching song no.1\n" +
                "Fetching song no.2\n" +
                "Fetching albums cancelled\n" +
                "Fetching albums ended\n" +
                "Fetching albums job completed",
                outputStreamCaptor.toString()
                        .trim())
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }
}