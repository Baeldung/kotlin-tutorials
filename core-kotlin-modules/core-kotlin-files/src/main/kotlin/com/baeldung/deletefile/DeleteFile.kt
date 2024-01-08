package com.baeldung.deletefile

import java.io.File
import java.io.IOException

fun deleteFile(filePath: String) {
    val file = File(filePath)
    if (file.exists() && file.isFile) {
        file.delete()
    }
}

fun deleteDirectory(directory: File) {
    if (directory.exists() && directory.isDirectory) {
        directory.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                deleteDirectory(file)
            } else {
                file.delete()
            }
        }
        directory.delete()
    }
}

fun safeDeleteDirectory(directory: File) {
    try {
        deleteDirectory(directory)
    } catch (e: IOException) {
        e.printStackTrace()  // Or handle the exception as needed
    }
}

fun File.deleteContentsRecursively(): Boolean {
    if (!this.exists()) return false
    if (!this.isDirectory) return this.delete()
    return this.walkBottomUp().all { it.delete() }
}

val success = File("/path/to/directory").deleteRecursively()