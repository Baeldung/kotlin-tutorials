package com.baeldung.textfile

import java.io.File

object TextWriter {

    fun writeText(content: String, fileName: String): Result<Unit> = kotlin.runCatching {
        File(fileName).writeText(content, Charsets.UTF_8)
    }

    fun writeTextSequence(sequence: Sequence<String>, fileName: String): Result<Unit> = kotlin.runCatching {
        File(fileName).printWriter(Charsets.UTF_8).buffered(100).use { writer ->
            sequence.forEach { element ->
                writer.write(element)
            }
        }
    }

}