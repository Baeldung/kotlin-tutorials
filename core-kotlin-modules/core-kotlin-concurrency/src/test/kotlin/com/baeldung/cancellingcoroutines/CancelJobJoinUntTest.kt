package com.baeldung.cancellingcoroutines

import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CancelJobJoinUntTest {
    private val standardOut = System.out
    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun whenCancelJobJoin_output() {
        println("Fetching album no.0 (line 1)")
        println("Fetching album no.1 (line 2)")
        println("Fetching album no.2 (line 3)")
        println("Fetching albums cancelled (line 4)")
        println("Fetching albums ended, (line 5)")
        println("Fetching albums job completed, lines printed: 5")
        Assert.assertEquals("Fetching album no.0 (line 1)\n" +
                "Fetching album no.1 (line 2)\n" +
                "Fetching album no.2 (line 3)\n" +
                "Fetching albums cancelled (line 4)\n" +
                "Fetching albums ended, (line 5)\n" +
                "Fetching albums job completed, lines printed: 5",
                outputStreamCaptor.toString()
                        .trim())
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }
}