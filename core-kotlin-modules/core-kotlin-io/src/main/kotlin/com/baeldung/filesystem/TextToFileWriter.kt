package com.baeldung.filesystem

import java.io.File

class TextToFileWriter {
    fun createTextFileUsingFileOutputStream(fileName: String, text: String) =
            File(fileName).outputStream().use { stream -> stream.write(text.toByteArray()) }

    fun createTextFileUsingPrintWriter(fileName: String, text: String) =
            File(fileName).printWriter().use { pw -> pw.print(text) }

    fun createTextFileUsingBufferedWriter(fileName: String, text: String) =
            File(fileName).bufferedWriter().use { bufferedWriter -> bufferedWriter.write(text) }
}