package com.baeldung

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

import java.nio.file.NoSuchFileException
import java.nio.file.Path

import kotlin.io.path.copyToRecursively
import kotlin.io.path.createParentDirectories

class PathUtilUnitTest {

    @BeforeEach
    fun setUp() {
        File("/tmp/source").mkdirs()
        File("/tmp/source/child").mkdirs()
        val helloWorldFile = File("/tmp/source/child/helloworld.txt")
        helloWorldFile.writeText("Hello, World!")

        Assertions.assertTrue(File("/tmp/source/child").exists())

        Assertions.assertFalse(File("/tmp/dest").exists())
        Assertions.assertFalse(File("/tmp/dest/child").exists())
    }

    @AfterEach
    fun tearDown() {
        File("/tmp/source").deleteRecursively()
        File("/tmp/dest").deleteRecursively()
        Assertions.assertFalse(File("/tmp/source").exists())
        Assertions.assertFalse(File("/tmp/dest").exists())
    }

    @kotlin.io.path.ExperimentalPathApi
    @Test
    fun `test copyToRecursively with createParentDirectories copies successfully`() {
        Path.of("/tmp", "dest", "child").createParentDirectories()
        Path.of("/tmp", "source", "child").copyToRecursively(
            Path.of("/tmp", "dest", "child"),
            followLinks = false
        )
        Assertions.assertTrue(File("/tmp/dest/child").exists())
        Assertions.assertTrue(File("/tmp/dest/child/helloworld.txt").exists())
    }

    @kotlin.io.path.ExperimentalPathApi
    @Test
    fun `test copyToRecursively without createParentDirectories fails`() {
        assertThrows<NoSuchFileException> {
            Path.of("/tmp", "source", "child").copyToRecursively(
                Path.of("/tmp", "dest", "child"),
                followLinks = false
            )
        }
    }
}