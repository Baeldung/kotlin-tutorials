package com.baeldung.runKotlinScripts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

// requires `kotlinc` to be installed on the machine.
class RunKotlinScriptsIntegrationTest {
    @Test
    fun `run Kotlin script with ProcessBuilder`() {
        val scriptFile = File.createTempFile("test", ".kts")
        scriptFile.writeText("""
        println("Hello, ProcessBuilder!")
        """.trimIndent())

        val output = runScriptUsingProcess(scriptFile.absolutePath).trim()
        assertEquals("Hello, ProcessBuilder!", output)
    }
}

fun runScriptUsingProcess(scriptPath: String): String {
    val processBuilder = ProcessBuilder("kotlinc", "-script", scriptPath)
    val process = processBuilder.start()
    return process.inputStream.bufferedReader().use { it.readText() }
}