package com.baeldung.files.text

import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.file.Path
import kotlin.io.path.bufferedWriter
import kotlin.io.path.isDirectory
import kotlin.io.path.outputStream


private const val DEFAULT_BUFFER_SIZE = 1024

object FileCreator {

    fun createFileByCreateNewFile(parentFile: File, filename: String): File {
        require(parentFile.isDirectory) {
            "$parentFile needs to be a directory."
        }
        val newFile = parentFile.resolve(filename)
        require(newFile.createNewFile()) {
            "Failed to create file '$newFile' because it already exist."
        }
        return newFile
    }

    fun createFileByWriteText(parentFile: File, filename: String, content: String): File {
        require(parentFile.isDirectory) {
            "$parentFile needs to be a directory."
        }
        val newFile = parentFile.resolve(filename)
        newFile.writeText(content)
        return newFile
    }

    fun createFileByBufferedWriter(parentFile: Path, filename: String, content: String): Path {
        require(parentFile.isDirectory()) {
            "$parentFile needs to be a directory."
        }
        val newFile = parentFile.resolve(filename)
        newFile.bufferedWriter().use {
            it.write(content)
        }
        return newFile
    }

    fun createFileUsingStream(parentFile: Path, filename: String, inputStream: InputStream): Path {
        require(parentFile.isDirectory()) {
            "$parentFile needs to be a directory."
        }
        val newFile = parentFile.resolve(filename)
        newFile.outputStream().buffered(DEFAULT_BUFFER_SIZE).use {
            inputStream.copyTo(it)
        }
        return newFile
    }

    fun createFileUsingChannel(parentFile: File, filename: String, content: String): File {
        require(parentFile.isDirectory) {
            "$parentFile needs to be a directory."
        }
        val newFile = parentFile.resolve(filename)
        val raf = RandomAccessFile(newFile, "rw")
        raf.channel.use {
            it.write(ByteBuffer.wrap(content.toByteArray()))
        }
        return newFile
    }
}
