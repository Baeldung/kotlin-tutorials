package com.baeldung.files.creation

import java.io.File

class FileCreator {

    fun createFileWithCreateNewFileMethod(fileName: String): Boolean = File(fileName).createNewFile()

    fun createFileWithWriteTextMethod(fileName: String, fileContent: String) =
            File(fileName).writeText(fileContent)

    fun createFileWithWriteByteMethod(fileName: String, fileContent: String) =
            File(fileName).writeBytes(fileContent.toByteArray())
}