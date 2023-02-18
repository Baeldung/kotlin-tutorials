package com.baeldung.textfiles

import java.io.File
import java.io.PrintWriter
import java.lang.Exception

object WritingTextFilesExample {

    fun writeToFileUsingWriteText(filename: String, n: Int) {
        var numbers = ""
        (1..n).forEach {
            numbers += "$it\n"
        }
        File(filename).writeText(numbers)
    }

    fun writeToFileUsingPrintWriter(filename: String, n: Int) {
        File(filename).printWriter().use { writer ->
            (1..n).forEach {
                writer.println(it)
            }
        }
    }

    fun writeToFileUsingBufferedWriter(filename: String, n: Int) {
        File(filename).bufferedWriter().use { writer ->
            (1..n).forEach {
                writer.appendLine("$it")
            }
        }
    }

    fun writeToFileLikeInJava(filename: String, n: Int) {
        val writer = PrintWriter(filename)
        try {
            (1..n).forEach {
                writer.println(it)
            }
        } catch (ex: Exception) {
            // handle error
        } finally {
            // ensure we close the file, even if error happens
            writer.close()
        }
    }
}