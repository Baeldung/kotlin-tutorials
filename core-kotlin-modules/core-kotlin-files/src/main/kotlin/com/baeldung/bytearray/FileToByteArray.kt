package com.baeldung.bytearray

import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun fileToByteArray(filePath: String): ByteArray {
    val file = File(filePath)
    return file.readBytes()
}


fun readFileUsingReader(filePath: String): String {
    val file = File(filePath)
    val contentBuilder = StringBuilder()

    file.reader().use { reader ->
        reader.forEachLine { line ->
            contentBuilder.append(line).append("\n")
        }
    }

    return contentBuilder.toString()
}

fun largeFileToByteArray(filePath: String, outputFilePath: String) {
    File(filePath).bufferedReader().use { reader ->
        BufferedOutputStream(FileOutputStream(outputFilePath)).use { bufferOut ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                bufferOut.write(line!!.toByteArray())
            }
        }
    }
}

fun safeFileToByteArray(filePath: String): ByteArray? {
    return try {
        File(filePath).readBytes()
    } catch (e: IOException) {
        println("Error reading file: ${e.message}")
        null
    }
}
