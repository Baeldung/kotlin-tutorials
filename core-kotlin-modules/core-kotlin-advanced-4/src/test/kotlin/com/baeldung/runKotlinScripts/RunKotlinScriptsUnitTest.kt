package com.baeldung.runKotlinScripts

import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.PumpStreamHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import javax.script.ScriptEngineManager

class RunKotlinScriptsUnitTest {
    @Test
    fun `run Kotlin script with ProcessBuilder`() {
        val scriptFile = File.createTempFile("test", ".kts")
        scriptFile.writeText("""
        println("Hello, ProcessBuilder!")
    """.trimIndent())

        val output = runScriptUsingProcess(scriptFile.absolutePath).trim()
        assertEquals("Hello, ProcessBuilder!", output)
    }

    @Test
    fun `run Kotlin script using ScriptEngine`() {
        val scriptFile = File.createTempFile("test", ".kts")
        scriptFile.writeText("""
        println("Hello from ScriptEngine!")
    """.trimIndent())

        val output = runKotlinScriptWithEngine(scriptFile.absolutePath).trim()

        assertEquals("Hello from ScriptEngine!", output)
    }


}

fun runScriptUsingProcess(scriptPath: String): String {
    val processBuilder = ProcessBuilder("kotlinc", "-script", scriptPath)
    val process = processBuilder.start()

    return process.inputStream.bufferedReader().use { it.readText() }
}

fun runKotlinScriptWithEngine(scriptPath: String): String {
    val engine = ScriptEngineManager().getEngineByExtension("kts")
    val script = File(scriptPath).readText()

    val outputStream = ByteArrayOutputStream()
    val originalOut = System.out
    System.setOut(PrintStream(outputStream))

    try {
        engine.eval(script)
    } finally {
        System.setOut(originalOut)
    }

    return outputStream.toString()
}

