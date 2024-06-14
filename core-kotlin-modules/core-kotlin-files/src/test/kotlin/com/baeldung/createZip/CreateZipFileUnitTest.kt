package com.baeldung.createZip

import net.lingala.zip4j.ZipFile
import net.lingala.zip4j.model.ZipParameters
import net.lingala.zip4j.model.enums.CompressionLevel
import net.lingala.zip4j.model.enums.CompressionMethod
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream
import org.apache.commons.compress.utils.IOUtils
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class CreateZipFileUnitTest {

    val filesToZip = listOf(File("file1.txt").apply { writeText("Hello, World!") }, File("file2.txt").apply { writeText("Kotlin ZIP Test") })

    @Test
    fun `create  zip file with java standard library`() {
        val outputZipFile = File("test_output.zip")

        createZipFile(filesToZip, outputZipFile)

        assertTrue(outputZipFile.exists())
        assertTrue(outputZipFile.length() > 0)

        filesToZip.forEach { it.delete() }
        outputZipFile.delete()
    }

    @Test
    fun `create zip file with apache commons compress`() {
        val outputZipFile = File("test_output_with_apache.zip")

        createZipFileWithApache(filesToZip, outputZipFile)

        assertTrue(outputZipFile.exists())
        assertTrue(outputZipFile.length() > 0)

        // Clean up
        filesToZip.forEach { it.delete() }
        outputZipFile.delete()
    }

    @Test
    fun `create zip file with Zip4j library`() {
        val outputZipFile = File("test_output_with_zip4j.zip")

        createZipFileWithZip4j(filesToZip, outputZipFile)

        assertTrue(outputZipFile.exists())
        assertTrue(outputZipFile.length() > 0)

        filesToZip.forEach { it.delete() }
        outputZipFile.delete()
    }
}
fun createZipFile(files: List<File>, outputZipFile: File) {
    ZipOutputStream(FileOutputStream(outputZipFile)).use { zipOut ->
        files.forEach { file ->
            FileInputStream(file).use { fis ->
                val zipEntry = ZipEntry(file.name)
                zipOut.putNextEntry(zipEntry)
                fis.copyTo(zipOut)
                zipOut.closeEntry()
            }
        }
    }
}

fun createZipFileWithApache(files: List<File>, outputZipFile: File) {
    ZipArchiveOutputStream(FileOutputStream(outputZipFile)).use { zipOut ->
        files.forEach { file ->
            zipOut.putArchiveEntry(ZipArchiveEntry(file.name))
            FileInputStream(file).use { fis ->
                IOUtils.copy(fis, zipOut)
            }
            zipOut.closeArchiveEntry()
        }
    }
}

fun createZipFileWithZip4j(files: List<File>, outputZipFile: File) {
    val zipFile = ZipFile(outputZipFile)
    val parameters = ZipParameters().apply {
        compressionMethod = CompressionMethod.DEFLATE
        compressionLevel = CompressionLevel.NORMAL
    }
    zipFile.addFiles(files, parameters)
}