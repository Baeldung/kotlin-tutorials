package com.baeldung.deleteFileContent

import org.apache.commons.io.FileUtils
import java.io.*
import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.StandardOpenOption

class DeleteFileContent {
    fun withWriteText(file: File) {
        file.writeText("")
    }

    fun withFileWriter(file: File) {
        FileWriter(file).close()
    }

    fun withPrintWriter(file: File) {
        PrintWriter(file).close()
    }

    fun withFileOutputStream(file: File) {
        FileOutputStream(file).close()
    }

    fun withBufferedWriter(file: File) {
        BufferedWriter(FileWriter(file)).close()
    }

    fun withRandomAccessFile(file: File) {
        RandomAccessFile(file, "rw").setLength(0)
    }

    fun withFileChannel(file: File) {
        FileChannel.open(file.toPath(), StandardOpenOption.WRITE).truncate(0).close()
    }

    fun withFiles(file: File) {
        Files.write(file.toPath(), byteArrayOf(), StandardOpenOption.TRUNCATE_EXISTING)
    }

    fun withFileUtils(file: File) {
        FileUtils.write(file, "", Charsets.UTF_8)
    }
}