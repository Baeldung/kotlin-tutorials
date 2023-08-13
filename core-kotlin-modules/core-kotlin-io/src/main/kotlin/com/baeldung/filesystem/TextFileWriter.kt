package com.baeldung.filesystem

import java.io.File

class TextFileWriter {

    fun createNewFile(pathname: String) = File(pathname).createNewFile()

    fun writeFile(pathname: String, textBytes: ByteArray) = File(pathname).writeBytes(textBytes)

    fun writeFile(pathname: String, text: String) = File(pathname).writeText(text)

    fun writeFileWithPrinterWriter(pathname: String, text: String) =
            File(pathname).printWriter().use { it.print(text) }

    fun writeFileWithBufferedWriter(pathname: String, text: String) =
            File(pathname).bufferedWriter(bufferSize = 4 * 1024).use { it.write(text) }

    fun writeFileWithBufferedWriterAppend(pathname: String, vararg textArgs: String) {
        val bufferedWriter = File(pathname).bufferedWriter()
        textArgs.forEach { text -> bufferedWriter.append(text) }
        bufferedWriter.close()
    }

}