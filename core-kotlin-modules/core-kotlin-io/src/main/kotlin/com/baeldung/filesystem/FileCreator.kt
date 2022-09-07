package com.baeldung.filesystem

import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths

class FileCreator {

    fun createFileUsingFileApi(fileName: String) {
        kotlin.runCatching {
            val file = File(fileName)
            val result = file.createNewFile()
            if (result) {
                FILE_CREATION_SUCCESS_MESSAGE
            } else {
                FILE_CREATION_FAILURE_MESSAGE
            }
        }.onSuccess { result ->
            println(result)
        }.onFailure { throwable ->
            println("$FILE_CREATION_EXCEPTION_MESSAGE $throwable")
        }
    }

    fun createFileUsingFilesApi(fileName: String) {
        kotlin.runCatching {
            val path = Paths.get(fileName)
            val result = Files.createFile(path)
            if (result != null) {
                FILE_CREATION_SUCCESS_MESSAGE
            } else {
                FILE_ALREADY_EXISTS_FAILURE_MESSAGE
            }
        }.onSuccess { result ->
            println(result)
        }.onFailure { throwable ->
            println("$FILE_CREATION_EXCEPTION_MESSAGE $throwable")
        }
    }

    fun createFileUsingFileOutputStream(fileName: String) {
        kotlin.runCatching {
            FileOutputStream(fileName).use { out ->
                println(FILE_CREATION_SUCCESS_MESSAGE)
            }
        }.onFailure { throwable ->
            println("$FILE_CREATION_EXCEPTION_MESSAGE $throwable")
        }
    }

    fun createFileUsingGoogleGuava(fileName: String) {
        kotlin.runCatching {
            val file = File(fileName)
            com.google.common.io.Files.touch(file)
        }.onSuccess {
            println(FILE_CREATION_SUCCESS_MESSAGE)
        }.onFailure { throwable ->
            println("$FILE_CREATION_EXCEPTION_MESSAGE $throwable")
        }
    }

    companion object {
        const val FILE_CREATION_SUCCESS_MESSAGE = "File created successfully."
        const val FILE_CREATION_FAILURE_MESSAGE = "File creation failed."
        const val FILE_CREATION_EXCEPTION_MESSAGE = "Exception occurred while creating new file."
        const val FILE_ALREADY_EXISTS_FAILURE_MESSAGE = "File creation failed, because file already exists."
    }
}