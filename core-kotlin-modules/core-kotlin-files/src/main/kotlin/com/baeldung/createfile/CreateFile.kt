package com.baeldung.createfile

import java.io.File

fun createEmptyFile(filePath: String): File {
    val file = File(filePath)

    file.createNewFile()
    return file
}
