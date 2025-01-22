package com.baeldung.runKotlinScripts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import javax.script.ScriptEngineManager

class RunKotlinScriptsUnitTest {

    @Test
    fun `run Kotlin script using ScriptEngine`() {
        val scriptFile = File.createTempFile("test", ".kts")
        scriptFile.writeText("""
        println("Hello from ScriptEngine!")
    """.trimIndent())

        val output = runKotlinScriptWithEngine(scriptFile.absolutePath).trim()
        println("JUST TEST")
        assertEquals("Hello from ScriptEngine!", output)
    }


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

