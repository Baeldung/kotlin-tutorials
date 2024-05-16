package com.baeldung.tempfile

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.createTempFile
import kotlin.io.path.writeText

fun createTempFileWithKotlinExtensions(): Path {
    val tempFile = createTempFile(prefix = "kotlinTemp", suffix = ".tmp")
    println("Temporary file created with Kotlin extensions at: ${tempFile.toAbsolutePath()}")

    tempFile.writeText("Kotlin Path Data")

    return tempFile
}

fun createCustomTempFileWithKotlinExtensions(): Path {
    val customDir = Paths.get(System.getProperty("java.io.tmpdir"))
    val tempFile = createTempFile(directory = customDir, prefix = "customKotlinTemp", suffix = ".tmp")
    println("Custom temporary file created with Kotlin extensions at: ${tempFile.toAbsolutePath()}")

    tempFile.writeText("Custom Kotlin Path Data")

    return tempFile
}

@Deprecated("This method is deprecated due to permissioning issues. Use kotlin.io.path.createTempFile instead.")
fun deprecatedCreateTempFile(): File {
    val tempFile = kotlin.io.createTempFile()
    println("Deprecated temporary file created at: ${tempFile.absolutePath}")
    tempFile.writeText("Deprecated Data")
    return tempFile
}

fun createTempFile(): File {
    val tempFile = File.createTempFile("temp", ".tmp")
    println("Temporary file created at: ${tempFile.absolutePath}")
    tempFile.writeText("Sample Data")
    tempFile.deleteOnExit()
    return tempFile
}

fun createCustomTempFile(): File {
    val tempDir = System.getProperty("java.io.tmpdir")
    val tempFile = File.createTempFile("customTemp", ".tmp", File(tempDir))
    tempFile.deleteOnExit()
    println("Custom temporary file created at: ${tempFile.absolutePath}")
    tempFile.writeText("Custom Data")
    return tempFile
}

