package com.baeldung.runKotlinScripts

import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.PumpStreamHandler
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import javax.script.ScriptEngineManager
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class RunKotlinScriptsUnitTest {

    @Test
    fun `run Kotlin script using kotlin script library`() {
        val scriptFile = File.createTempFile("kotlintest_file", ".kts")
        scriptFile.writeText("""
        println("Hello from Kotlin script!")
    """.trimIndent())

        runKotlinScript(scriptFile.absolutePath)

        val output = scriptFile.readText()
        assertTrue(output.contains("Hello from Kotlin script!"))
    }

    @Test
    fun `run Kotlin script with ProcessBuilder`() {
        val scriptFile = File.createTempFile("test", ".kts")
        scriptFile.writeText("""
        println("Hello, ProcessBuilder!")
    """.trimIndent())

        runScriptUsingProcess(scriptFile.absolutePath)

        val output = scriptFile.readText()
        assertTrue(output.contains("Hello, ProcessBuilder!"))
    }

    @Test
    fun `run Kotlin script using ScriptEngine`() {
        val scriptFile = File.createTempFile("test", ".kts")
        scriptFile.writeText("""
        println("Hello from ScriptEngine!")
    """.trimIndent())

        runKotlinScriptWithEngine(scriptFile.absolutePath)

        val output = scriptFile.readText()
        assertTrue(output.contains("Hello from ScriptEngine!"))
    }

    @Test
    fun `run Kotlin script using Apache Commons Exec`() {
        val scriptFile = File.createTempFile("commonsExecTest", ".kts")
        scriptFile.writeText("""
        println("Hello, Apache Commons Exec!")
    """.trimIndent())

        runScriptWithCommonsExec(scriptFile.absolutePath)

        assertTrue(scriptFile.readText().contains("Hello, Apache Commons Exec!"))
    }

}

fun runKotlinScript(scriptPath: String) {
    val scriptSource = scriptPath.toScriptSource()

    val compilationConfiguration = ScriptCompilationConfiguration {
        jvm {
            dependenciesFromCurrentContext(wholeClasspath = true)
        }
    }

    val evaluationConfiguration = ScriptEvaluationConfiguration()

    val scriptingHost = BasicJvmScriptingHost()

    val result = scriptingHost.eval(scriptSource, compilationConfiguration, evaluationConfiguration)

    result.reports.forEach {
        println(it.message)
    }
}

fun runScriptUsingProcess(scriptPath: String) {
    val processBuilder = ProcessBuilder("kotlinc", "-script", scriptPath)
    val process = processBuilder.start()

    process.inputStream.bufferedReader().use {
        println(it.readText())
    }

    process.waitFor()
}

fun runKotlinScriptWithEngine(scriptPath: String) {
    val engine = ScriptEngineManager().getEngineByExtension("kts")
    val script = File(scriptPath).readText()

    engine.eval(script)
}

fun runScriptWithCommonsExec(scriptPath: String) {
    val commandLine = CommandLine.parse("kotlinc -script $scriptPath")

    val outputStream = ByteArrayOutputStream()
    val streamHandler = PumpStreamHandler(outputStream)

    val executor = DefaultExecutor()
    executor.streamHandler = streamHandler

    try {
        executor.execute(commandLine)
        println("Script Output: ${outputStream.toString()}")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}