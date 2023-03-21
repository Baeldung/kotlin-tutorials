package com.baeldung.paths

import com.baeldung.paths.PathExamples.cp
import com.baeldung.paths.PathExamples.ls
import com.baeldung.paths.PathExamples.mv
import com.baeldung.paths.PathExamples.countLines
import com.baeldung.paths.PathExamples.readFileContent
import com.baeldung.paths.PathExamples.readLines
import com.baeldung.paths.PathExamples.softDelete
import com.baeldung.paths.PathExamples.writeFileLineByLine
import com.baeldung.paths.PathExamples.writeTextAllAtOnce
import com.baeldung.paths.PathExamples.writeTextAllAtOnceInNewFile
import java.nio.file.FileAlreadyExistsException
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.absolutePathString
import kotlin.io.path.createFile
import kotlin.io.path.createTempDirectory
import kotlin.io.path.deleteRecursively
import kotlin.io.path.div
import kotlin.io.path.exists
import kotlin.io.path.name
import kotlin.io.path.notExists
import kotlin.io.path.pathString
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.io.path.writeLines
import kotlin.io.path.writeText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@OptIn(ExperimentalPathApi::class)
class PathExamplesUnitTest {

    @Test
    fun `ls - should list directory content`() {
        val tempDirectory = createTempDirectory()

        // list empty directory
        assertEquals(ls(tempDirectory.pathString), emptyList<Path>())

        // Add a new file
        val fileName = "tmp-file.txt"
        (tempDirectory / fileName).createFile()

        // List non-empty directory
        val dirContent = ls(tempDirectory.pathString)
        assertEquals(1, dirContent.size)
        assertEquals(fileName, dirContent.first().name)

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `cp - should copy file to destination path`() {
        val tempDirectory = createTempDirectory()
        val sourceFile = tempDirectory / "input-file.txt"
        sourceFile.writeText(FILE_CONTENT)

        val destinationFile = tempDirectory / "output-file.txt"
        check(destinationFile.notExists())

        cp(sourceFile.absolutePathString(), destinationFile.absolutePathString())

        assertTrue(sourceFile.exists()) // cp do not delete the source
        assertTrue(destinationFile.exists())
        assertEquals(FILE_CONTENT, destinationFile.readText())

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `mv - should move a file to destination path`() {
        val tempDirectory = createTempDirectory()
        val sourceFile = tempDirectory / "input-file.txt"
        sourceFile.writeText(FILE_CONTENT)

        val destinationFile = tempDirectory / "output-file.txt"
        check(destinationFile.notExists())

        mv(sourceFile.absolutePathString(), destinationFile.absolutePathString())

        assertTrue(sourceFile.notExists()) // mv delete the source
        assertTrue(destinationFile.exists())
        assertEquals(FILE_CONTENT, destinationFile.readText())

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `softDelete - should move a file to deleted items directory`() {
        val tempDirectory = createTempDirectory()
        val sourceFile = tempDirectory / "input-file.txt"
        sourceFile.writeText(FILE_CONTENT)

        softDelete(sourceFile.absolutePathString())

        assertTrue(sourceFile.notExists()) // original file is deleted

        val destinationFile = (tempDirectory / ".deleted" / sourceFile.name)
        assertTrue(destinationFile.exists())
        assertEquals(FILE_CONTENT, destinationFile.readText())

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `writeTextAllAtOnceInNewFile - should throw FileAlreadyExistsException if file already exist`() {
        val tempDirectory = createTempDirectory()

        val outputFile = tempDirectory / "output-file.txt"
        outputFile.writeText(FILE_CONTENT)
        assertTrue(outputFile.exists())

        assertThrows<FileAlreadyExistsException> {
            writeTextAllAtOnceInNewFile(
                outputFile.absolutePathString(),
                FILE_CONTENT
            )
        }

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `writeTextAllAtOnce - should override the file if it already exist`() {
        val tempDirectory = createTempDirectory()

        val outputFile = tempDirectory / "output-file.txt"
        outputFile.writeText(FILE_CONTENT)
        assertTrue(outputFile.exists())

        val overwriteContent = "DIFFERENT_CONTENT"
        writeTextAllAtOnce(
            outputFile.absolutePathString(),
            overwriteContent
        )
        assertEquals(overwriteContent, outputFile.readText())

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `writeFileLineByLine - should write the numbers from 1 to 10 into the file`() {
        val tempDirectory = createTempDirectory()

        val outputFile = tempDirectory / "output-file.txt"
        writeFileLineByLine(
            outputFile.absolutePathString()
        )
        assertThat(outputFile.readLines()).hasSize(10)

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `readFileContent - should read the file content as string`() {
        val tempDirectory = createTempDirectory()

        val file = tempDirectory / "input-file.txt"
        val content = "test\nfile\ncontent"
        file.writeText(content)

        val readFileContent = readFileContent(file.absolutePathString())
        assertEquals(content, readFileContent)

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `readFileContent - should read the file as list of lines`() {
        val tempDirectory = createTempDirectory()

        val file = tempDirectory / "input-file.txt"
        val lines = listOf("test", "file", "content")
        file.writeLines(lines)

        val readFileLines = readLines(file.absolutePathString())
        assertEquals(lines, readFileLines)

        tempDirectory.deleteRecursively()
    }

    @Test
    fun `countLines - should count the lines in a file`() {
        val tempDirectory = createTempDirectory()

        val file = tempDirectory / "input-file.txt"
        val lines = listOf("test", "file", "content")
        file.writeLines(lines)

        val linesCount = countLines(file.absolutePathString())
        assertEquals(lines.size, linesCount)

        tempDirectory.deleteRecursively()
    }

    companion object {
        const val FILE_CONTENT = "test file content"
    }
}